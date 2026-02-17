import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeCategorie } from './liste-categorie';

describe('ListeCategorie', () => {
  let component: ListeCategorie;
  let fixture: ComponentFixture<ListeCategorie>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListeCategorie]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListeCategorie);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
