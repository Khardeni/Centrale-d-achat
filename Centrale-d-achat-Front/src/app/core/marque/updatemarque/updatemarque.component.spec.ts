import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatemarqueComponent } from './updatemarque.component';

describe('UpdatemarqueComponent', () => {
  let component: UpdatemarqueComponent;
  let fixture: ComponentFixture<UpdatemarqueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdatemarqueComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatemarqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
