import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http'; // Importez withFetch
import { BASE_PATH } from './api';
import { authInterceptor } from './auth-interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes), 
    
    provideClientHydration(withEventReplay()),
    provideHttpClient(withInterceptors([authInterceptor])),
    
    // 1. Ajout de withFetch() pour supprimer l'erreur NG02801
    provideHttpClient(withFetch( )), 
    
    { provide: BASE_PATH, useValue:'http://localhost:8080' }
  ]
};
