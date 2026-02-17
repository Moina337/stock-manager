import { Component, inject, PLATFORM_ID } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute, RouterLink,RouterLinkActive, RouterOutlet } from '@angular/router';
import { toSignal } from '@angular/core/rxjs-interop';
import { filter, map, startWith } from 'rxjs';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { SessionService } from '../../core/services/session-service';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [RouterLink,RouterLinkActive, CommonModule, RouterOutlet],
  templateUrl: './layout.html',
})
export class Layout {
  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private platformId = inject(PLATFORM_ID);
  public sessionService = inject(SessionService);

  titrePage = toSignal(
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd),
      startWith(null),
      map(() => {
        let currentRoute = this.route;
        
        // On descend dans l'arborescence de manière sécurisée
        while (currentRoute.firstChild) {
          currentRoute = currentRoute.firstChild;
        }

        // On vérifie si snapshot et title existent avant de lire
        // Si rien n'est trouvé, on retourne 'Tableau de bord' par défaut
        return currentRoute?.snapshot?.title || 'Tableau de bord';
      })
    ),
    { initialValue: 'Chargement...' }
  );

  deconnecter() {
    // 1. On vérifie qu'on est sur le navigateur pour accéder au localStorage
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('token'); // On supprime le badge
    }
    
    // 2. On redirige immédiatement vers le login
    // L'AuthGuard bloquera alors tout accès aux pages protégées
    this.router.navigate(['/login']);
  }
}
