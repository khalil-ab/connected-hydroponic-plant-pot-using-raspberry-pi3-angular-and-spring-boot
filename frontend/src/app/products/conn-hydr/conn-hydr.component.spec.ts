import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConnHydrComponent } from './conn-hydr.component';

describe('ConnHydrComponent', () => {
  let component: ConnHydrComponent;
  let fixture: ComponentFixture<ConnHydrComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConnHydrComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConnHydrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
