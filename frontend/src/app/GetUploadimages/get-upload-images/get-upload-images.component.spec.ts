import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetUploadImagesComponent } from './get-upload-images.component';

describe('GetUploadImagesComponent', () => {
  let component: GetUploadImagesComponent;
  let fixture: ComponentFixture<GetUploadImagesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetUploadImagesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetUploadImagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
