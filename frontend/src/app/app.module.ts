import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {TasksComponent} from './tasks/tasks.component';
import {TasksListComponent} from './tasks/tasks-list/tasks-list.component';
import {TaskService} from "./tasks/task.service";
import {TasksAddComponent} from './tasks/tasks-add/tasks-add.component';
import {RegisterComponent} from "./auth/register/register.component";
import {RegisterService} from "./auth/register/register.service";
import {RouterModule} from "@angular/router";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {AppRoutingModule} from "./app-routing.module";
import {HandleError} from "./error/handle-error";
import {LoginComponent} from "./auth/login/login.component";
import {LoginService} from "./auth/login/login.service";
import {HeaderComponent} from "./core/header/header.component";
import {ErrorComponent} from "./error/error.component";
import {LogoutService} from "./auth/logout/logout.service";
import {CookieService} from 'ngx-cookie-service';
import { AppsComponent } from './apps/apps.component';
import { AppsAddComponent } from './apps/apps-add/apps-add.component';
import { AppsListComponent } from './apps/apps-list/apps-list.component';
import {AppsService} from "./apps/apps.service";

@NgModule({
    declarations: [
        AppComponent,
        TasksComponent,
        TasksListComponent,
        TasksAddComponent,
        RegisterComponent,
        LoginComponent,
        HeaderComponent,
        ErrorComponent,
        PageNotFoundComponent,
        AppsComponent,
        AppsAddComponent,
        AppsListComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        ReactiveFormsModule,
        RouterModule,
        AppRoutingModule
    ],
    providers: [
        AppsService,
        TaskService,
        RegisterService,
        LoginService,
        LogoutService,
        HandleError,
        CookieService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
