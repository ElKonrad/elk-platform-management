import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GrokComponent } from './grok.component';

describe('GrokComponent', () => {
  let component: GrokComponent;
  let fixture: ComponentFixture<GrokComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GrokComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GrokComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
