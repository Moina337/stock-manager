import { Component, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProduitsService } from '../../api';
import { CategorieService } from '../../api';
import { CategorieDTO } from '../../api/model/categorieDTO';
import { ProduitAjoutDTO } from '../../api';
import { OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-ajout-produit',
  imports: [CommonModule, FormsModule],
  templateUrl: './ajout-produit.html',
  styleUrl: './ajout-produit.css',
})
// ... (vos imports restent identiques)
export class AjoutProduit implements OnInit {
  categories: CategorieDTO[] = [];
  imageFile: File | undefined;
  imagePreview: string | null = null;

  nouveauProduit: any = {
    // Utilisez 'any' pour éviter les conflits de type avec l'image
    nom: '',
    prix: 0,
    categorieId: undefined,
  };

  // AJOUTEZ 'private' ici pour pouvoir utiliser this.http
  constructor(
    private http: HttpClient,
    private produitsService: ProduitsService,
    private categoriesService: CategorieService,
    private cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.categoriesService.listCategories().subscribe((data) => {
      this.categories = data;
      this.cdr.markForCheck();
    });
  }
  validerAjout() {
    // 1. Vérification simple des champs
    if (!this.nouveauProduit.nom || !this.nouveauProduit.prix || !this.nouveauProduit.categorieId) {
      alert('Veuillez remplir tous les champs obligatoires.');
      return;
    }

    // 2. On prépare l'objet DTO (sans FormData)
    const produitDTO: ProduitAjoutDTO = {
      nom: this.nouveauProduit.nom,
      prix: this.nouveauProduit.prix,
      categorieId: this.nouveauProduit.categorieId,
    };

    // 3. On appelle le service avec les deux arguments
    // Note : remplacez 'this.selectedFile' par votre variable qui contient le Blob/File de l'image
    this.produitsService.ajouterProduit(produitDTO, this.imageFile).subscribe({
      next: (response) => {
        alert('Produit ajouté avec succès !');
        this.resetForm();
      },
      error: (error) => {
        console.error("Erreur lors de l'ajout du produit :", error);
        alert("Une erreur est survenue lors de l'ajout du produit.");
      },
    });
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.imageFile = file; // On stocke le binaire ici
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result as string;
        this.cdr.markForCheck();
      };
      reader.readAsDataURL(file);
    }
  }

  resetForm() {
    this.nouveauProduit = { nom: '', prix: 0, categorieId: undefined };
    this.imageFile = undefined;
    this.imagePreview = null;
    this.cdr.markForCheck();
  }

  supprimerImage(): void {
    this.imageFile = undefined;
    this.imagePreview = null;
  }
}
