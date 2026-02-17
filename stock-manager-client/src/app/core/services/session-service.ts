import { Injectable, signal, inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private router = inject(Router);
  private platformId = inject(PLATFORM_ID);
  
  // On utilise un signal pour que le nom soit réactif partout
  nomUtilisateur = signal<string>('Utilisateur');

  constructor() {
    this.extraireNomDuToken();
  }

  // Méthode pour lire l'email (sub) dans le JWT
  extraireNomDuToken(): void {
    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('token');
      if (token) {
        try {
          // Un JWT est composé de 3 parties séparées par des points. 
          // La 2ème partie (index 1) contient les données (payload).
          const payload = JSON.parse(atob(token.split('.')[0]));
          this.nomUtilisateur.set(payload.sub || 'Utilisateur');
        } catch (e) {
          this.nomUtilisateur.set('Utilisateur');
        }
      }
    }
  }

  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('token');
    }
    this.nomUtilisateur.set('Utilisateur');
    this.router.navigate(['/login']);
  }
}
