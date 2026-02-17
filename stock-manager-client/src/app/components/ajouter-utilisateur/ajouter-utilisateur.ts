import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms';
import { UtilisateurDTO } from '../../api';
import { AuthService } from '../../api';
import { Router } from '@angular/router';


@Component({
  selector: 'app-ajouter-utilisateur',
  imports: [CommonModule, FormsModule, RouterLink ],
  templateUrl: './ajouter-utilisateur.html',
  styleUrl: './ajouter-utilisateur.css',
})
export class AjouterUtilisateur {

 private router = inject(Router);
   
  utilisateur: UtilisateurDTO = {
    nom: '',
    email: '',
    password: '',  
  } 

    comfirmationMotDePasse: string = '';

  private utilisateurService= inject(AuthService);

  ajouterUtilisateur(): void {

    if(this.utilisateur.password !=this.comfirmationMotDePasse){
      alert("Les mots de passe ne correspondent pas.");
      return;
    }

    this.utilisateurService.registerUser(this.utilisateur).subscribe({
      next: (response) => {
        alert('Utilisateur ajouté avec succès !');
        // Optionnel : rediriger vers la liste des utilisateurs ou une autre page
        this.router.navigate(['/utilisateurs']);
        
      },
      error: (err) => {
        console.error('Erreur lors de l\'ajout de l\'utilisateur:', err);
        alert('Erreur lors de l\'ajout de l\'utilisateur. Veuillez réessayer.');
      }
    });

  }

  }


