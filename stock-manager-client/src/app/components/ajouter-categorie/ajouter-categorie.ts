import { Component } from '@angular/core';
import { CategorieDTO, CategorieService } from '../../api'; // Imports regroupés
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router'; // Pour la redirection

@Component({
  selector: 'app-ajouter-categorie',
  standalone: true,
  imports: [CommonModule, FormsModule], // Ajout de CommonModule ici
  templateUrl: './ajouter-categorie.html',
  styleUrl: './ajouter-categorie.css',
})
export class AjouterCategorie {

  NvCategorie = {
    nom: '',
  };

  constructor(
    private categorieService: CategorieService,
    private router: Router // Injection du router
  ) {}

  validerAjout(): void {
    // Vérification de sécurité
    if (!this.NvCategorie.nom.trim()) {
      alert('Le nom de la catégorie est obligatoire.');
      return;
    }

    const categorieDTO: CategorieDTO = {
      nom: this.NvCategorie.nom,
    };

    this.categorieService.ajoutCategorie(categorieDTO).subscribe({
      next: () => {
        alert('Catégorie ajoutée avec succès !');
        // Redirection vers la liste pour voir le résultat
        this.router.navigate(['/categories']); 
      },
      error: (err) => {
        console.error('Erreur lors de l\'ajout :', err);
        alert('Erreur lors de l\'ajout de la catégorie.');
      }
    });
  }
}
