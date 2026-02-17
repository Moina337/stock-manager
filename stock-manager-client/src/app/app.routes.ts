import { Routes } from '@angular/router';
import { ListeProduits } from './components/liste-produits/liste-produits';
import { AjoutProduit } from './components/ajout-produit/ajout-produit';
import { ModifierProduit } from './components/modifier-produit/modifier-produit';
import { ListeCategorie } from './components/liste-categorie/liste-categorie';
import { ModifierCategorie } from './components/modifier-categorie/modifier-categorie';
import { AjouterCategorie } from './components/ajouter-categorie/ajouter-categorie';
import { Login } from './components/login/login';
import { Layout } from './components/layout/layout';
import { authGuard } from './core/guards/auth-guard';
import { ListeUtilisateurs } from './components/liste-utilisateurs/liste-utilisateurs';
import { AjouterUtilisateur } from './components/ajouter-utilisateur/ajouter-utilisateur';

export const routes: Routes = [
  // 1. Route publique (SANS Layout)
  {
    path: 'login',
    component: Login,
    title: 'Connexion',
  },

  // 2. Routes sécurisées (AVEC Layout)
  {
    path: '',
    component: Layout, // Le Layout contient la Sidebar et le RouterOutlet
    title: 'Gestion de produits',
    canActivate: [authGuard], // Optionnel: protège toutes les sous-pages
    children: [
      { path: 'produits', component: ListeProduits, title: 'Liste des produits' },
      { path: 'ajouter-produit', component: AjoutProduit, title: 'Ajouter un produit' },
      { path: 'modifier-produit/:id', component: ModifierProduit, title: 'Modifier un produit' },
      { path: 'categories', component: ListeCategorie, title: 'Liste des catégories' },
      {path: 'modifier-categorie/:id', component: ModifierCategorie, title: 'Modifier une catégorie',},
      { path: 'ajouter-categorie', component: AjouterCategorie, title: 'Ajouter une catégorie' },
      { path: 'utilisateurs', component: ListeUtilisateurs, title: 'Liste des utilisateurs' },
      {path:  'ajouter-utilisateur', component: AjouterUtilisateur, title: 'Ajouter un utilisateur'},
      { path: 'login', component: Login },
    ],
  },

  // Redirection par défaut vers login si rien ne correspond
  { path: '**', redirectTo: 'login' },
];
