import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router'; // Pour rediriger après login
import { AuthService, LoginRequestDTO } from '../../api';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule], // Ajoute FormsModule ici
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  // Correction de la syntaxe de l'objet (utilisez ':' et non '=')
  loginRequest: LoginRequestDTO = {
    email: '',
    password: '' // Vérifie l'orthographe 'password' selon ton DTO
  };

  private authService = inject(AuthService);
  private router = inject(Router);

  login(): void {
    if (!this.loginRequest.email || !this.loginRequest.password) {
      alert('Veuillez remplir tous les champs');
      return;
    }

    this.authService.login(this.loginRequest).subscribe({
      next: (response) => {
        // 1. Stocker le token dans le localStorage pour rester connecté
        if (response.token) {
          localStorage.setItem('token', response.token);
          
          alert('Connexion réussie !');
          // 2. Rediriger vers le dashboard ou les produits
          this.router.navigate(['/produits']);
        }
      },
      error: (err) => {
        console.error('Erreur login:', err);
        alert('Email ou mot de passe incorrect');
      }
    });
  }
}
