import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const platformId = inject(PLATFORM_ID);
  const router = inject(Router);

  // 1. Protection contre le SSR (on ne touche au localStorage que sur le navigateur)
  if (isPlatformBrowser(platformId)) {
    const token = localStorage.getItem('token');

    if (token) {
      // 2. On injecte le badge de sécurité
      const authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });

      // 3. Gestion des erreurs de retour (ex: Token expiré)
      return next(authReq).pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.status === 401) {
            // Le badge n'est plus valide -> on nettoie et on sort
            localStorage.removeItem('token');
            router.navigate(['/login']);
          }
          return throwError(() => error);
        })
      );
    }
  }

  // Si pas de token ou côté serveur, on laisse passer la requête normale
  return next(req);
};
