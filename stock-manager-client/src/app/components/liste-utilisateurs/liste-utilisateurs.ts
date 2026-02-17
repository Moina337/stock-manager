import { Component, inject, signal,computed } from '@angular/core';
import { RouterLink,RouterLinkActive } from '@angular/router';
import { UtilisateurDTO } from '../../api';
import { UtilisateurService } from '../../api';
import { CommonModule } from '@angular/common';
import { toSignal } from '@angular/core/rxjs-interop';
import { NgxPaginationModule } from 'ngx-pagination';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-liste-utilisateurs',
  imports: [RouterLink,RouterLinkActive,CommonModule,NgxPaginationModule,FormsModule],
  templateUrl: './liste-utilisateurs.html',
  styleUrl: './liste-utilisateurs.css',
})
export class ListeUtilisateurs {


   pageActuelle = signal(1);


   rechercheNom= signal('');

   private utilisateurservice =inject(UtilisateurService);

   utilisateurs = toSignal(this.utilisateurservice.listeUtilisateur(), { initialValue: [] as UtilisateurDTO[] });

   supprimerUtilisateur(id: number): void {
    this.utilisateurservice.deleteUtilisateur(id).subscribe({
      next: () => {
        alert('Utilisateur supprimé !');
        window.location.reload(); // Rafraîchit la page pour voir les changements
      },
      error: (err) => {
        console.error('Erreur suppression:', err);
        alert('Erreur lors de la suppression.');
      }
    });
   } 

  UtilisateursFiltres = computed(() => {
    const texte = this.rechercheNom().toLowerCase();
    if (!texte) {
      return this.utilisateurs();
    }

    return this.utilisateurs().filter(utilisateur => {
      const matchNom = utilisateur.nom?.toLowerCase().includes(texte);
      const matchEmail = utilisateur.email?.toLowerCase().includes(texte);
      return matchNom || matchEmail;
    });
   });

   updateNom(event: Event) {
    this.rechercheNom.set((event.target as HTMLInputElement).value);
   }
  }


