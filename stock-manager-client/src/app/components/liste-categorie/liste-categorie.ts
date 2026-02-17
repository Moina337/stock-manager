import { Component, signal,computed } from '@angular/core';
import { CategorieDTO } from '../../api';
import { CategorieService } from '../../api';
import { CommonModule } from '@angular/common';
import { toSignal } from '@angular/core/rxjs-interop';
import { NgxPaginationModule } from 'ngx-pagination';
import { RouterLink } from '@angular/router';



@Component({
  standalone: true,
  selector: 'app-liste-categorie',
  imports: [ CommonModule,NgxPaginationModule,RouterLink],
  templateUrl: './liste-categorie.html',
  styleUrl: './liste-categorie.css',
})
export class ListeCategorie {

 categories;

 page:number =1;

 nbParPage= signal(1);

 rechercheNom= signal('');  

 
 constructor(private categorieService: CategorieService) {
   this.categories= toSignal(this.categorieService.listCategories(), { initialValue: [] as CategorieDTO[] });
 }

categoriesFiltres = computed(()=>{
  const texte = this.rechercheNom().toLowerCase();
  return this.categories().filter(categorie=>{
    const matchNom=categorie.nom?.toLowerCase().includes(texte);
    return matchNom;
  })
})

updateNom(event: Event){

  this.rechercheNom.set((event.target as HTMLInputElement).value);

}

supprimerCategorie(id:number): void{
 if (confirm('Voulez-vous vraiment supprimer ce produit ?')){
  this.categorieService.supprimerCategorie(id).subscribe({
     next: ()=>{
      alert("Categorie supprimé avec succès !")
      window.location.reload();
     },
     error: (err)=>{
      console.error('Erreur suppression:', err);
        alert('Erreur lors de la suppression.');
     }
  })
 }

}



}
