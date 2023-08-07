import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HomeComponent } from './home.component';

fdescribe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [HomeComponent]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should show error message for same source and destination', () => {
    // Simulate setting same source and destination
    component.source = 'Pune';
    component.destination = 'Pune';
    component.searchTrains();
    expect(component.errormsg).toContain('Source and destination cannot be the same');
  });

  // Add more tests for other component behavior and UI interactions

  // Example of testing a DOM element's presence
  it('should render a search button', () => {
    const searchButton = fixture.nativeElement.querySelector('.search-button');
    expect(searchButton).toBeTruthy();
  });
});
