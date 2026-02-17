export * from './auth.service';
import { AuthService } from './auth.service';
export * from './categorie.service';
import { CategorieService } from './categorie.service';
export * from './produits.service';
import { ProduitsService } from './produits.service';
export * from './utilisateur.service';
import { UtilisateurService } from './utilisateur.service';
export const APIS = [AuthService, CategorieService, ProduitsService, UtilisateurService];
