import {Component, OnInit} from '@angular/core';
import {MonitoredApplication} from "../../domain/request/monitored-application";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {UserResponse} from "../../domain/response/user-response";
import {ErrorResponse} from "../../error/error-response";
import {AppsService} from "../apps.service";

@Component({
    selector: 'app-apps-add',
    templateUrl: './apps-add.component.html',
    styleUrls: ['./apps-add.component.css']
})
export class AppsAddComponent implements OnInit {

    addApplicationForm: FormGroup;
    application: MonitoredApplication = new MonitoredApplication();
    responseError: ErrorResponse;
    responseStatus: number;
    userResponse: UserResponse = new UserResponse();

    constructor(private appsService: AppsService,
                private fb: FormBuilder,
                private router: Router) {
    }

    ngOnInit() {
        this.addApplicationForm = this.fb.group({
            name: ['', [Validators.required, Validators.pattern('^[a-z0-9_-\\s]+$')]],
            filePath: ['', [Validators.required, Validators.pattern('(([^\\\\/]*[\\\\/])*)([^\\\\/]+)$')]],
            grok: ['', [Validators.required]]
        });
    }

    addApp() {
        this.application = this.addApplicationForm.getRawValue();
        this.appsService.addApplication(this.application)
            .subscribe(
                res => {
                    this.addApplicationForm.reset();
                    this.appsService.onAppAdded.emit(this.application);
                },
                err => {
                    this.responseStatus = err.status;
                    this.responseError = err.json();
                });
    }

}
