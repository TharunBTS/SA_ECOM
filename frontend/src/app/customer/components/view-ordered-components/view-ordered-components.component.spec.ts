import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewOrderedComponentsComponent } from './view-ordered-components.component';

describe('ViewOrderedComponentsComponent', () => {
  let component: ViewOrderedComponentsComponent;
  let fixture: ComponentFixture<ViewOrderedComponentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewOrderedComponentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewOrderedComponentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
