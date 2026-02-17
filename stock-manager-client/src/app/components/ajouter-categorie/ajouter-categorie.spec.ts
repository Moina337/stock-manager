import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjouterCategorie } from './ajouter-categorie';

describe('AjouterCategorie', () => {
  let component: AjouterCategorie;
  let fixture: ComponentFixture<AjouterCategorie>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AjouterCategorie]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AjouterCategorie);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
