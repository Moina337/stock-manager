import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';

import { BASE_PATH } from './api';
import { authInterceptor } from './auth-interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),

    provideClientHydration(withEventReplay()),

    // ✅ UN SEUL HttpClient propre
    provideHttpClient(
      withFetch(),
      withInterceptors([authInterceptor])
    ),

    { provide: BASE_PATH, useValue: 'http://localhost:8080' }
  ]
};