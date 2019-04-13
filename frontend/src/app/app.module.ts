import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {AppComponent} from './app.component';
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
import {LeftmenuComponent} from "./core/leftmenu/leftmenu.component";
import { ElasticComponent } from './apps/elastic/elastic.component';
import { KibanaComponent } from './apps/kibana/kibana.component';
import { GrokComponent } from './apps/grok/grok.component';

@NgModule({
    declarations: [
        AppComponent,
        RegisterComponent,
        LoginComponent,
        HeaderComponent,
        ErrorComponent,
        PageNotFoundComponent,
        AppsComponent,
        AppsAddComponent,
        AppsListComponent,
        LeftmenuComponent,
        ElasticComponent,
        KibanaComponent,
        GrokComponent
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
