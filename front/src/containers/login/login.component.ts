import { Component, OnInit, Output } from "@angular/core";
import { StudentService } from "../../services/student.service";
import { Student } from "../../models/student.model";
import { Account } from "../../models/account.model";
import { EventEmitter } from "../../../node_modules/protractor";
import { Router } from "@angular/router";
import { first } from "../../../node_modules/rxjs/operators";
import { HttpResponse } from "@angular/common/http";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
  accounts: Account[];

  constructor(private studentService: StudentService, private router: Router) {}

  ngOnInit() {
    this.studentService.getAccounts().subscribe(s => (this.accounts = s));
  }
  /*
  onLogin(event: Account) {
    let accountMap: Map<string, string> = new Map<string, string>()
    {{
      accountMap.set("uname", event.uname);
      accountMap.set("pass", event.pass);
    }};


   this.studentService.login(accountMap);

    accountMap.set("uname", event.uname);
  accountMap.set("pass", event.pass);
      
      this.router.navigate(['/','login']);

      
     this.router.navigate(['/','home']);
      
    }

    */

  onLogin(event: Account) {
    this.studentService.login(event);
  }
}
