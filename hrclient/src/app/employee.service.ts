import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';
import {environment} from '../environments/environment';
import {Employee} from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) {
  }
  private baseUrl = environment.apiUrl + '/employees';
  private orgchartUrl = environment.apiUrl + '/orgchart';
  private dataChanged: Subject<any> = new Subject<any>();
  public dataObservable: Observable<any> = this.dataChanged.asObservable();

  getEmployee(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(`${this.baseUrl}`, employee);
  }

  updateEmployee(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteEmployee(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, {responseType: 'text'});
  }

  getEmployeesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getHierachReport(): Observable<any> {
    return this.http.get(`${this.orgchartUrl}`);
  }

  dataChangedOperation(operation) {
    this.dataChanged.next(operation);
  }

  getObservable() {
    return this.dataChanged;
  }
}
