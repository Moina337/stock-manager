import { ChangeDetectorRef, Component } from '@angular/core';
import { CategorieDTO, ProduitDTO } from '../../api';
import { CategorieService } from '../../api';
import { CategorieUpdateDTO } from '../../api';
import { OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-modifier-categorie',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './modifier-categorie.html',
  styleUrl: './modifier-categorie.css',
})
export class ModifierCategorie implements OnInit {
  idCategorie: number = 0;
  categorie: CategorieDTO | null = null;

  constructor(
    private route: ActivatedRoute,
    private categoriervice: CategorieService,
    private cdr: ChangeDetectorRef,
    private router: Router,
  ) {}

  categorieUpdate: CategorieUpdateDTO = {
    nom: '',
  };

  ngOnInit(): void {
    this.idCategorie = Number(this.route.snapshot.paramMap.get('id'));

    this.chargerCategorie();
  }

  chargerCategorie(): void {
    this.categoriervice.afficheCategorieParId(this.idCategorie).subscribe({
      next: (categorie) => {
        this.categorie = categorie;
        this.categorieUpdate.nom = categorie.nom;
        this.cdr.markForCheck();
      },
      error: (error) => {
        console.error('Erreur lors du chargement de la catégorie:', error);
      },
    });
  }

  validerModif(): void {
    // On s'abonne à l'Observable pour exécuter la requête
    this.categoriervice.modifierCategorie(this.idCategorie, this.categorieUpdate).subscribe({
      next: () => {
        alert('Catégorie mise à jour avec succès !');
        this.router.navigate(['/categories']); // Redirection vers la liste
      },
      error: (err) => {
        console.error('Erreur lors de la modification:', err);
        alert('Une erreur est survenue.');
      },
    });
  }
}
