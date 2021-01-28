import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipAcesComponent } from './equip-aces.component';

describe('EquipAcesComponent', () => {
  let component: EquipAcesComponent;
  let fixture: ComponentFixture<EquipAcesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EquipAcesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EquipAcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
