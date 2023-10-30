import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpCallenderComponent } from './emp-callender.component';

describe('EmpCallenderComponent', () => {
  let component: EmpCallenderComponent;
  let fixture: ComponentFixture<EmpCallenderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmpCallenderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpCallenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
