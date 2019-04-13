import { Component, OnInit } from '@angular/core';
import {AppsService} from "../apps.service";
import {MonitoredApplication} from "../../domain/request/monitored-application";

@Component({
  selector: 'app-apps-list',
  templateUrl: './apps-list.component.html',
  styleUrls: ['./apps-list.component.css']
})
export class AppsListComponent implements OnInit {

  apps: MonitoredApplication[];

  constructor(private appsService: AppsService) { }

  ngOnInit() {
    this.appsService.getApplications().subscribe(
        (res: any) => {
          this.apps = res;
        }
    );
      this.appsService.onAppAdded.subscribe(
          (app: MonitoredApplication) =>  {
            this.apps.push(app);
          }
      );
  }

}
