import { ChangeDetectorRef, Component, EventEmitter, OnInit, Output } from '@angular/core';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  ngOnInit(): void {
    localStorage.removeItem('JWT_TOKEN')
    console.log("token removed");
    
  }

  @Output() methodCalled = new EventEmitter<void>();

  constructor(private cdRef: ChangeDetectorRef, private sharedService: SharedService) {
    this.sharedService.changeLogin$.subscribe(() => {
      this.changelogin();
    });
  }
  login:boolean=true;

  changelogin()
  {
    this.login=false;
    console.log("changed")
    this.cdRef.detectChanges();
  }

  logout()
  {
    this.login=true;
    this.cdRef.detectChanges();
    localStorage.removeItem("JWT_TOKEN")
    window.alert("Logged Out Successfully");
  }

}


