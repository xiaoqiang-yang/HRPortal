import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UpdateEmployeeComponent} from './update-employee/update-employee.component';
import {CreateEmployeeComponent} from './create-employee/create-employee.component';
import {EmployeeListComponent} from './employee-list/employee-list.component';


const routes: Routes = [
  { path: '', redirectTo: 'employee', pathMatch: 'full' },
  { path: 'employees', component: EmployeeListComponent },
  { path: 'add', component: CreateEmployeeComponent },
  { path: 'update/:id', component: UpdateEmployeeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
