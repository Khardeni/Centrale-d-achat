import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditEmplacementComponent } from './edit-emplacement.component';

describe('EditEmplacementComponent', () => {
  let component: EditEmplacementComponent;
  let fixture: ComponentFixture<EditEmplacementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditEmplacementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditEmplacementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
