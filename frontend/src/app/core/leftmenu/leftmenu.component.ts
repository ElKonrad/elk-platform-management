import {Component, OnInit} from "@angular/core";
import {CookieService} from "ngx-cookie-service";
import {LoginService} from "../../auth/login/login.service";

@Component({
    selector: 'app-leftmenu',
    templateUrl: 'leftmenu.component.html',
    styleUrls: ['leftmenu.component.css']
})
export class LeftmenuComponent implements OnInit {

    private USERNAME_COOKIE: string = 'USERNAME';

    constructor(private cookieService: CookieService) {
    }

    ngOnInit() {
    }

    getUsername(): string {
        return this.cookieService.get(this.USERNAME_COOKIE);
    }


}