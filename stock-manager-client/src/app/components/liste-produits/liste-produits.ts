import { Component,signal,computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { ProduitDTO } from '../../api/model/produitDTO';
import { ProduitsService } from '../../api';
import { toSignal } from '@angular/core/rxjs-interop';
import { CategorieService } from '../../api';
import{ CategorieDTO } from '../../api/model/categorieDTO';



@Component({
  selector: 'app-liste-produits',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './liste-produits.html',
  styleUrls: ['./liste-produits.css']
})
export class ListeProduits {

  produits; // signal déclaré mais pas encore initialisé

  categories;

  // signal de nom
   
  rechercheNom= signal('');

  // signale categorie

   signalCategorie = signal<number | null>(null);
   

  constructor(private produitsService: ProduitsService,private CategorieService: CategorieService) {

    // initialisation après injection du service
    this.produits = toSignal(this.produitsService.listrTousProduits(), { initialValue: [] as ProduitDTO[] });

    this.categories = toSignal(this.CategorieService.listCategories(), { initialValue: [] as CategorieDTO[] });

    
  }

 supprimerProduit(id: number): void {
  if (confirm('Voulez-vous vraiment supprimer ce produit ?')) {
    this.produitsService.supprimerProduit(id).subscribe({
      next: () => {
        alert('Produit supprimé !');
        // Comme toSignal est en lecture seule, le plus simple ici 
        // est de recharger les données via le service ou de rafraîchir le composant
        window.location.reload(); 
      },
      error: (err) => {
        console.error('Erreur suppression:', err);
        alert('Erreur lors de la suppression.');
      }
    });
  }
}

produitFiltres= computed(() => {

const nom = this.rechercheNom().toLowerCase();
const categorieId = this.signalCategorie();

return this.produits().filter(produit => {
  const matchesNom = produit.nom.toLowerCase().includes(nom);
  const matchesCategorie = !categorieId || produit.categorieId === categorieId;
  return matchesNom && matchesCategorie;

})

});



// Méthode pour la catégorie (avec conversion en nombre)
updateCategorie(event: Event) {
  const select = event.target as HTMLSelectElement;
  const valeur = select.value;
  this.signalCategorie.set(valeur ? Number(valeur) : null);
}

// 1. Ajoute ces signaux
pageActuelle = signal(1);
taillePage = signal(4); // Nombre de produits par page

// 2. Calcule la liste finale à afficher (découpée)
produitsPagines = computed(() => {
  const debut = (this.pageActuelle() - 1) * this.taillePage();
  const fin = debut + this.taillePage();
  
  // On prend la liste déjà filtrée et on la découpe
  return this.produitFiltres().slice(debut, fin);
});

// 3. Calcule le nombre total de pages pour l'affichage
totalSelection = computed(() => Math.ceil(this.produitFiltres().length / this.taillePage()));

// 4. Fonctions de navigation
changerPage(page: number) {
  this.pageActuelle.set(page);
}

// IMPORTANT : Réinitialise la page à 1 quand on filtre
updateRecherche(event: Event) {
  const input = event.target as HTMLInputElement;
  this.rechercheNom.set(input.value);
  this.pageActuelle.set(1); // Retour page 1
}


}
