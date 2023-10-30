import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImpotComponent } from './impot.component';

describe('ImpotComponent', () => {
  let component: ImpotComponent;
  let fixture: ComponentFixture<ImpotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImpotComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ImpotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
