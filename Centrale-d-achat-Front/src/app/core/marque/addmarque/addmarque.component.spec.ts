import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddmarqueComponent } from './addmarque.component';

describe('AddmarqueComponent', () => {
  let component: AddmarqueComponent;
  let fixture: ComponentFixture<AddmarqueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddmarqueComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddmarqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
