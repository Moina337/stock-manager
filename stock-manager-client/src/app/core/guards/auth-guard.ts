import { inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Router, CanActivateFn } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);

  // ÉTAPE 1 : Si on est sur le serveur (Node.js), on dit "OUI" 
  // pour laisser la page charger et laisser le navigateur vérifier après.
  if (!isPlatformBrowser(platformId)) {
    return true; 
  }

  // ÉTAPE 2 : On est dans le navigateur, on vérifie le VRAI token
  const token = localStorage.getItem('token');
  
  if (token) {
    return true; // Accès validé !
  }

  // ÉTAPE 3 : Pas de token trouvé dans le navigateur -> Direction Login
  router.navigate(['/login']);
  return false;
};
