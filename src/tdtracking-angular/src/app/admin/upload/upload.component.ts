import { Output } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { UploadService } from '../upload.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  @Output() fileLoadedEvent = new EventEmitter();

  uploadedFile: File;
  constructor(private serviceUpload: UploadService) {}

  ngOnInit() {}

  fileChange(element) {
    this.uploadedFile = element.target.files[0];
  }

  upload() {
    let formData = new FormData();
      formData.append("file",this.uploadedFile);
    this.serviceUpload.uploadFile(formData, '/api/v1/careers/upload-sheets').subscribe((res)=> {
      console.log('response received is ', res);
      this.fileLoadedEvent.emit();
    });
    }

}
