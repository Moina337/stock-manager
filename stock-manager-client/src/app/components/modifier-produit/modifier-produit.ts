import { Component, ChangeDetectorRef, ChangeDetectionStrategy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router'; // Ajoutez Router
import { ProduitDTO, ProduitsService, CategorieService, CategorieDTO, ProduitUpdateDTO } from '../../api';

@Component({
  selector: 'app-modifier-produit',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './modifier-produit.html',
  styleUrl: './modifier-produit.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ModifierProduit implements OnInit {
  idProduit: number = 0;
  produit: ProduitDTO | null = null;
  categories: CategorieDTO[] = [];

  produitImageUrl: string | null = null; // Pour afficher l'image actuelle du produit
  
  selectedFile: File | null = null;
  imagePreview: string | null = null;

  produitUpdate: ProduitUpdateDTO = {
    nom: '',
    prix: 0,
    categorieId: 0,
  };

  constructor(
    private route: ActivatedRoute,
    private router: Router, // Pour rediriger après succès
    private produitsService: ProduitsService,
    private categoriesService: CategorieService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.idProduit = Number(this.route.snapshot.paramMap.get('id'));

    // Charger les catégories
    this.categoriesService.listCategories().subscribe(cats => {
      this.categories = cats;
      this.cdr.markForCheck();
    });
   
    this.chargerProduit();
   
  } 

   // Charger le produit
    chargerProduit(): void {
   this.produitsService.afficherProduitParId(this.idProduit).subscribe({
      next: (data) => {
        this.produit = data;  
        this.produitUpdate = {
          nom: data.nom,
          prix: data.prix,  
          categorieId: data.categorie?.id || 0
        };
        this.cdr.markForCheck();
      },
      error: (err) => console.error('Erreur chargement:', err)
    }); 
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result as string;
        this.cdr.markForCheck();
      };
      reader.readAsDataURL(file);
    }
  }

  supprimerImageSelectionnee() {
    this.selectedFile = null;
    this.imagePreview = null;
  }

  

  validerModification(): void {
    // On utilise le service généré qui attend (id, DTO, image)
    // On passe undefined si selectedFile est null pour respecter la signature
    this.produitsService.modifierProduit(this.idProduit, this.produitUpdate, this.selectedFile || undefined).subscribe({
      next: () => {
        alert('Produit mis à jour avec succès !');
        this.router.navigate(['/produits']);
      },
      error: (err) => {
        console.error('Erreur modification:', err);
        alert('Erreur lors de la modification.');
      }
    });
  }
}
