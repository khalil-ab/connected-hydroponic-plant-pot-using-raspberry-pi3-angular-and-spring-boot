import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyplantsComponent } from './myplants.component';

describe('MyplantsComponent', () => {
  let component: MyplantsComponent;
  let fixture: ComponentFixture<MyplantsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyplantsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyplantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
