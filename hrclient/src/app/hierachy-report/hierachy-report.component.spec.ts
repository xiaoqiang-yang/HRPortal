import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HierachyReportComponent } from './hierachy-report.component';

describe('HierachyReportComponent', () => {
  let component: HierachyReportComponent;
  let fixture: ComponentFixture<HierachyReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HierachyReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HierachyReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
