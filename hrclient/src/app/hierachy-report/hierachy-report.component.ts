import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {EmployeeService} from '../employee.service';

@Component({
  selector: 'app-hierachy-report',
  templateUrl: './hierachy-report.component.html',
  styleUrls: ['./hierachy-report.component.css']
})
export class HierachyReportComponent implements OnInit {
  reportTable: any;
  levels: number[];
  constructor(private employeeService: EmployeeService,
              private router: Router) {
  }
  ngOnInit(): void {
    this.loadReportTable();
    this.employeeService.getObservable().subscribe(() => this.loadReportTable());
  }
  loadReportTable() {
    this.employeeService.getHierachReport().subscribe(data => {
      const totalCol = data.maxCol + 1;
      this.levels = Array(totalCol).fill(0).map((x, i) => i);
      this.reportTable = data.sparseItems;
    });

  }
}
