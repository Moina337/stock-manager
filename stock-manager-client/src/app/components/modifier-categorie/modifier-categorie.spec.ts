import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifierCategorie } from './modifier-categorie';

describe('ModifierCategorie', () => {
  let component: ModifierCategorie;
  let fixture: ComponentFixture<ModifierCategorie>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModifierCategorie]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModifierCategorie);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
