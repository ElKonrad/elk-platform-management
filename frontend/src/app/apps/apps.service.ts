import {EventEmitter, Injectable} from '@angular/core';
import {Http, Response} from "@angular/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {MonitoredApplication} from "../domain/request/monitored-application";
import {HandleError} from "../error/handle-error";

@Injectable()
export class AppsService {

    onAppAdded = new EventEmitter<MonitoredApplication>();

    private appsUrl = "/api/application";

    constructor(private http: Http, private handleError: HandleError) {
    }

    addApplication(app: MonitoredApplication) {
        return this.http.post(this.appsUrl, app)
            .catch(err => this.handleError.handleError(err));
    }

    getApplications() {
        return this.http.get(this.appsUrl)
            .map(
                (response: Response) => {
                    console.log(response);
                    return response.json();
                }
            )
            .catch(err => this.handleError.handleError(err));
    }

}
