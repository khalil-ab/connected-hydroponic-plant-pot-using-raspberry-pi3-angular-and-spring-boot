import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MaintRepComponent } from './maint-rep.component';

describe('MaintRepComponent', () => {
  let component: MaintRepComponent;
  let fixture: ComponentFixture<MaintRepComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MaintRepComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MaintRepComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
