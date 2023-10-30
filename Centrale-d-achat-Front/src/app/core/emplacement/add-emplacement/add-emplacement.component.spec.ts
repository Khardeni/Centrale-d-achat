import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEmplacementComponent } from './add-emplacement.component';

describe('AddEmplacementComponent', () => {
  let component: AddEmplacementComponent;
  let fixture: ComponentFixture<AddEmplacementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEmplacementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddEmplacementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
