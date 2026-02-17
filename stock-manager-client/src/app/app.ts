import { Component, signal } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { Layout } from './components/layout/layout';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [Layout,RouterLink,RouterOutlet],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {
  protected readonly title = signal('gestion-produit-crud');
}
