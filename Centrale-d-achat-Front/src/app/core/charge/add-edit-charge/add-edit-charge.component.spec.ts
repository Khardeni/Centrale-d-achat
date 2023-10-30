import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditChargeComponent } from './add-edit-charge.component';

describe('AddEditChargeComponent', () => {
  let component: AddEditChargeComponent;
  let fixture: ComponentFixture<AddEditChargeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEditChargeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddEditChargeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
