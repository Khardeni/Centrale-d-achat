import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditImpotComponent } from './add-edit-impot.component';

describe('AddEditImpotComponent', () => {
  let component: AddEditImpotComponent;
  let fixture: ComponentFixture<AddEditImpotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEditImpotComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddEditImpotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
