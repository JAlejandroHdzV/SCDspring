(window.webpackJsonp=window.webpackJsonp||[]).push([[14],{"+fEi":function(e,t,i){"use strict";i.r(t),i.d(t,"RescuesModule",(function(){return be}));var a=i("2kYt"),s=i("40Bj"),o=i("nIj0"),r=i("m1yc"),n=i("PCNd"),l=i("sEIs"),c=i("OZ4H"),d=i("EM62"),b=i("jIqt"),u=i("YtkY"),m=i("IkLD"),h=i("qFEQ"),p=i("29Wa"),f=i("Cd2c"),g=i("ulve"),v=i("gcUQ"),y=i("mFH5");function B(e,t){if(1&e&&(d.Qb(0),d.Dc(1),d.Pb()),2&e){const e=d.ec();d.Bb(1),d.Ec(e.dictionary.translate("mandatoryField"))}}function R(e,t){1&e&&(d.Qb(0),d.Dc(1,"Folio Duplicado"),d.Pb())}function S(e,t){if(1&e&&(d.Qb(0),d.Dc(1),d.Pb()),2&e){const e=d.ec();d.Bb(1),d.Ec(e.dictionary.translate("maxOf")+"50 "+e.dictionary.translate("characters"))}}function F(e,t){if(1&e){const e=d.Tb();d.Sb(0,"mat-option",21),d.ac("onSelectionChange",(function(t){return d.sc(e),d.ec().spCodeChanged(t)})),d.Dc(1),d.Rb()}if(2&e){const e=t.$implicit;d.jc("value",e),d.Bb(1),d.Fc(" ",e.spCode," ")}}function C(e,t){if(1&e&&(d.Qb(0),d.Dc(1),d.Pb()),2&e){const e=d.ec();d.Bb(1),d.Ec(e.dictionary.translate("mandatoryField"))}}function x(e,t){if(1&e&&(d.Qb(0),d.Dc(1),d.Pb()),2&e){const e=d.ec();d.Bb(1),d.Ec(e.dictionary.translate("maxOf")+"250 "+e.dictionary.translate("characters"))}}function D(e,t){if(1&e&&(d.Qb(0),d.Dc(1),d.Pb()),2&e){const e=d.ec();d.Bb(1),d.Ec(e.dictionary.translate("mandatoryField"))}}function j(e,t){if(1&e&&(d.Qb(0),d.Dc(1),d.Pb()),2&e){const e=d.ec();d.Bb(1),d.Ec(e.dictionary.translate("maxOf")+"500 "+e.dictionary.translate("characters"))}}function E(e,t){if(1&e&&(d.Qb(0),d.Dc(1),d.Pb()),2&e){const e=d.ec();d.Bb(1),d.Ec(e.dictionary.translate("mandatoryField"))}}function N(e,t){if(1&e&&(d.Qb(0),d.Dc(1),d.Pb()),2&e){const e=d.ec();d.Bb(1),d.Ec(e.dictionary.translate("maxOf")+"500 "+e.dictionary.translate("characters"))}}function I(e,t){if(1&e&&(d.Qb(0),d.Dc(1),d.Pb()),2&e){const e=d.ec();d.Bb(1),d.Ec(e.dictionary.translate("mandatoryField"))}}function L(e,t){if(1&e&&(d.Qb(0),d.Dc(1),d.Pb()),2&e){const e=d.ec();d.Bb(1),d.Ec(e.dictionary.translate("maxOf")+"500 "+e.dictionary.translate("characters"))}}function w(e,t){if(1&e&&(d.Qb(0),d.Dc(1),d.Pb()),2&e){const e=d.ec();d.Bb(1),d.Ec(e.dictionary.translate("mandatoryField"))}}function M(e,t){if(1&e&&(d.Qb(0),d.Dc(1),d.Pb()),2&e){const e=d.ec();d.Bb(1),d.Ec(e.dictionary.translate("maxOf")+"500 "+e.dictionary.translate("characters"))}}function T(e,t){1&e&&(d.Sb(0,"mat-error"),d.Sb(1,"small"),d.Dc(2,"Licencia Qsol duplicada"),d.Rb(),d.Rb())}let k=(()=>{class e{constructor(e,t,i,a,s){this.fb=e,this.fs=t,this.iets=i,this.sps=a,this.spCodeLookupService=s,this.cummins=window.cummins,this.validDates=!0,this.disabledEvent=new d.o,this.optionsSp=[]}get folio(){return this.mainForm.get("folio")}get spCode(){return this.mainForm.get("spCode")}get name(){return this.mainForm.get("name")}get distributor(){return this.mainForm.get("distributor")}get failureDate(){return this.mainForm.get("failureDate")}get customer(){return this.mainForm.get("customer")}get responseTime(){return this.mainForm.get("responseTime")}get reparationTime(){return this.mainForm.get("reparationTime")}get tiempoMuerto(){return this.mainForm.get("tiempoMuerto")}get reason(){return this.mainForm.get("reason")}ngOnInit(){this.isNew=!this.data,this.setupForm(),this.sps.getSpCodes().subscribe(e=>{this.optionsSp=[...e],this.filteredOptions=this.mainForm.controls.spCode.valueChanges.pipe(Object(b.a)(""),Object(u.a)(e=>"string"==typeof e?e:e.spCode),Object(u.a)(e=>e?this._filter(e):this.optionsSp.slice()))})}displayFn(e){return e&&e.spCode?e.spCode:""}_filter(e){const t=e.toLowerCase();return this.optionsSp.filter(e=>e.spCode.toLowerCase().includes(t))}spCodeChanged(e){const t=e.source.value;this.name.setValue(t.providerName),this.distributor.setValue(t.responsibleBranchCode+" - "+t.responsibleBranchName)}showSpCodeLabelFn(e){return null==e?void 0:e.label}showCourseCodeLabelFn(e){return null==e?void 0:e.label}disableFields(){this.fs.disableFields(this.mainForm)}enableFields(){this.fs.enableFields(this.mainForm),this.isNew||(this.folio.disable(),this.spCode.disable(),this.name.disable(),this.distributor.disable(),this.failureDate.disable(),this.customer.disable(),this.responseTime.disable(),this.reparationTime.disable(),this.tiempoMuerto.disable(),this.reason.disable())}submit(){var e,t,i,a,s;const o=this.mainForm.value;let r={folio:null===(e=o.folio)||void 0===e?void 0:e.toUpperCase(),spCode:null===(t=o.spCode)||void 0===t?void 0:t.spCode,cliente:null===(i=o.customer)||void 0===i?void 0:i.toUpperCase(),fechaFalla:null===(a=o.failureDate)||void 0===a?void 0:a.getTime(),tiempoRespuesta:o.responseTime,tiempoReparacion:o.reparationTime,tiempoMuerto:o.tiempoMuerto,motivo:null===(s=o.reason)||void 0===s?void 0:s.toUpperCase(),del:"N"};return this.isNew?this.iets.save(r):this.iets.update(r,r.folio)}setupForm(){this.mainForm=this.fb.group({folio:[null,[o.s.required,o.s.maxLength(20)],this.duplicatedEvaluationPeriodsValidator.bind(this)],spCode:[null,[o.s.required]],name:null,distributor:null,failureDate:[null,[o.s.required]],customer:[null,[o.s.required,o.s.maxLength(250)]],responseTime:[null,[o.s.required,o.s.min(0)]],reparationTime:[null,[o.s.required,o.s.min(0)]],tiempoMuerto:[null,[o.s.required,o.s.min(0)]],reason:[null,[o.s.maxLength(500)]],del:"N"}),this.isNew||(this.folio.disable(),this.mainForm.patchValue({folio:this.data.folio,failureDate:new Date(this.data.failureDate),customer:this.data.cliente,responseTime:this.data.tiempoRespuesta,reparationTime:this.data.tiempoReparacion,tiempoMuerto:this.data.tiempoMuerto,reason:this.data.motivo,del:this.data.delete}),this.sps.getInfoSpcode(this.data.codes.spCode).subscribe(e=>{this.mainForm.patchValue({spCode:e[0],name:e[0].providerName,distributor:e[0].responsibleBranchCode+" - "+e[0].responsibleBranchName})}))}duplicatedEvaluationPeriodsValidator(e){return this.iets.findByCriteria({folio:e.value,del:"N"}).pipe(Object(u.a)(e=>{var t;return 0===e.length||1==e.length&&(null===(t=this.data)||void 0===t?void 0:t.folio)==e[0].folio?null:{duplicated:!0}}))}}return e.\u0275fac=function(t){return new(t||e)(d.Mb(o.d),d.Mb(r.g),d.Mb(r.r),d.Mb(r.s),d.Mb(m.a))},e.\u0275cmp=d.Gb({type:e,selectors:[["infL-main-form"]],inputs:{data:"data",dictionary:"dictionary"},decls:71,vars:34,consts:[["autocomplete","off",3,"formGroup"],["fxLayout","row","fxLayout.xs","column"],["fxFlex","100%"],["formControlName","folio","matInput","",3,"placeholder"],[4,"ngIf"],[1,"example-full-width"],["formControlName","spCode","matInput","","required","","type","text",3,"matAutocomplete","placeholder"],[3,"displayWith"],["auto","matAutocomplete"],[3,"value","onSelectionChange",4,"ngFor","ngForOf"],["formControlName","name","matInput","","readonly","",3,"placeholder"],["formControlName","distributor","matInput","","readonly","",3,"placeholder"],["fxFlex","50%"],["formControlName","failureDate","matInput","","readonly","",3,"matDatepicker","placeholder"],["matSuffix","",3,"for"],["failureDate",""],["formControlName","customer","matInput","",3,"placeholder"],["formControlName","responseTime","matInput","",3,"placeholder"],["formControlName","reparationTime","matInput","",3,"placeholder"],["formControlName","tiempoMuerto","matInput","",3,"placeholder"],["formControlName","reason","matInput","",3,"placeholder"],[3,"value","onSelectionChange"]],template:function(e,t){if(1&e&&(d.Sb(0,"form",0),d.Sb(1,"div",1),d.Sb(2,"div",2),d.Sb(3,"mat-form-field"),d.Nb(4,"input",3),d.Sb(5,"mat-error"),d.Bc(6,B,2,1,"ng-container",4),d.Bc(7,R,2,0,"ng-container",4),d.Bc(8,S,2,1,"ng-container",4),d.Rb(),d.Rb(),d.Rb(),d.Rb(),d.Sb(9,"div",1),d.Sb(10,"div",2),d.Sb(11,"mat-form-field",5),d.Nb(12,"input",6),d.Sb(13,"mat-autocomplete",7,8),d.Bc(15,F,2,2,"mat-option",9),d.fc(16,"async"),d.Rb(),d.Sb(17,"mat-error"),d.Dc(18),d.Rb(),d.Rb(),d.Rb(),d.Rb(),d.Sb(19,"div",1),d.Sb(20,"div",2),d.Sb(21,"mat-form-field"),d.Nb(22,"input",10),d.Rb(),d.Rb(),d.Rb(),d.Sb(23,"div",1),d.Sb(24,"div",2),d.Sb(25,"mat-form-field"),d.Nb(26,"input",11),d.Rb(),d.Rb(),d.Rb(),d.Sb(27,"div",1),d.Sb(28,"div",12),d.Sb(29,"mat-form-field"),d.Nb(30,"input",13),d.Nb(31,"mat-datepicker-toggle",14),d.Nb(32,"mat-datepicker",null,15),d.Sb(34,"mat-error"),d.Dc(35),d.Rb(),d.Rb(),d.Rb(),d.Rb(),d.Sb(36,"div",1),d.Sb(37,"div",2),d.Sb(38,"mat-form-field"),d.Nb(39,"input",16),d.Sb(40,"mat-error"),d.Bc(41,C,2,1,"ng-container",4),d.Bc(42,x,2,1,"ng-container",4),d.Rb(),d.Rb(),d.Rb(),d.Rb(),d.Sb(43,"div",1),d.Sb(44,"div",12),d.Sb(45,"mat-form-field"),d.Nb(46,"input",17),d.Sb(47,"mat-error"),d.Bc(48,D,2,1,"ng-container",4),d.Bc(49,j,2,1,"ng-container",4),d.Rb(),d.Rb(),d.Rb(),d.Sb(50,"div",12),d.Sb(51,"mat-form-field"),d.Nb(52,"input",18),d.Sb(53,"mat-error"),d.Bc(54,E,2,1,"ng-container",4),d.Bc(55,N,2,1,"ng-container",4),d.Rb(),d.Rb(),d.Rb(),d.Rb(),d.Sb(56,"div",1),d.Sb(57,"div",12),d.Sb(58,"mat-form-field"),d.Nb(59,"input",19),d.Sb(60,"mat-error"),d.Bc(61,I,2,1,"ng-container",4),d.Bc(62,L,2,1,"ng-container",4),d.Rb(),d.Rb(),d.Rb(),d.Rb(),d.Sb(63,"div",1),d.Sb(64,"div",2),d.Sb(65,"mat-form-field"),d.Nb(66,"input",20),d.Sb(67,"mat-error"),d.Bc(68,w,2,1,"ng-container",4),d.Bc(69,M,2,1,"ng-container",4),d.Rb(),d.Rb(),d.Rb(),d.Rb(),d.Bc(70,T,3,0,"mat-error",4),d.Rb()),2&e){const e=d.qc(14),i=d.qc(33);d.jc("formGroup",t.mainForm),d.Bb(4),d.jc("placeholder",t.dictionary.translate("folio")+" *"),d.Bb(2),d.jc("ngIf",t.folio.invalid&&t.folio.errors.required),d.Bb(1),d.jc("ngIf",t.folio.invalid&&t.folio.errors.duplicated),d.Bb(1),d.jc("ngIf",t.folio.invalid&&t.folio.errors.maxlength),d.Bb(4),d.jc("matAutocomplete",e)("placeholder",t.dictionary.translate("spCode")),d.Bb(1),d.jc("displayWith",t.displayFn),d.Bb(2),d.jc("ngForOf",d.gc(16,32,t.filteredOptions)),d.Bb(3),d.Fc(" ",t.dictionary.translate("mandatoryField")," "),d.Bb(4),d.jc("placeholder",t.dictionary.translate("name")),d.Bb(4),d.jc("placeholder",t.dictionary.translate("distributor")),d.Bb(4),d.jc("matDatepicker",i)("placeholder",t.dictionary.translate("failureDate")+" *"),d.Bb(1),d.jc("for",i),d.Bb(4),d.Fc(" ",t.dictionary.translate("mandatoryField")," "),d.Bb(4),d.jc("placeholder",t.dictionary.translate("customer")+" *"),d.Bb(2),d.jc("ngIf",t.customer.invalid&&t.customer.errors.required),d.Bb(1),d.jc("ngIf",t.customer.invalid&&t.customer.errors.maxlength),d.Bb(4),d.jc("placeholder",t.dictionary.translate("responseTime")+" *"),d.Bb(2),d.jc("ngIf",t.responseTime.invalid&&t.responseTime.errors.required),d.Bb(1),d.jc("ngIf",t.responseTime.invalid&&t.responseTime.errors.maxlength),d.Bb(3),d.jc("placeholder",t.dictionary.translate("reparationTime")+" *"),d.Bb(2),d.jc("ngIf",t.reparationTime.invalid&&t.reparationTime.errors.required),d.Bb(1),d.jc("ngIf",t.reparationTime.invalid&&t.reparationTime.errors.maxlength),d.Bb(4),d.jc("placeholder",t.dictionary.translate("deathTime")+" *"),d.Bb(2),d.jc("ngIf",t.tiempoMuerto.invalid&&t.tiempoMuerto.errors.required),d.Bb(1),d.jc("ngIf",t.tiempoMuerto.invalid&&t.tiempoMuerto.errors.maxlength),d.Bb(4),d.jc("placeholder",t.dictionary.translate("reason")),d.Bb(2),d.jc("ngIf",t.reason.invalid&&t.reason.errors.required),d.Bb(1),d.jc("ngIf",t.reason.invalid&&t.reason.errors.maxlength),d.Bb(1),d.jc("ngIf",t.mainForm.hasError("duplicated"))}},directives:[o.t,o.m,o.f,h.c,h.a,p.c,o.c,f.b,o.l,o.e,p.b,a.l,g.c,o.r,g.a,a.k,v.b,v.d,p.g,v.a,y.n],pipes:[a.b],styles:[".mat-form-field[_ngcontent-%COMP%]{min-inline-size:95%}"]}),e})(),O=(()=>{class e{constructor(e,t,i){this.data=e,this.dialogRef=t,this.message=i,this.loading=!1}ngOnInit(){this.title=this.data.dictionary.translate(this.data.rescue?"editRescue":"addRescue")}onClose(){this.dialogRef.close()}onSubmit(){this.loading=!0,this.mfc.disableFields(),this.mfc.submit().subscribe(e=>{this.loading=!1,this.mfc.enableFields(),this.dialogRef.close({})},e=>{this.loading=!1,this.mfc.enableFields(),this.message.genericHttpError()})}}return e.\u0275fac=function(t){return new(t||e)(d.Mb(c.a),d.Mb(c.f),d.Mb(r.k))},e.\u0275cmp=d.Gb({type:e,selectors:[["infL-main-form-dialog"]],viewQuery:function(e,t){var i;1&e&&d.Hc(k,!0),2&e&&d.pc(i=d.bc())&&(t.mfc=i.first)},decls:2,vars:7,consts:[[3,"disabled","loading","title","submitText","cancelText","closeEvent","submitEvent"],[2,"width","200px",3,"data","dictionary"]],template:function(e,t){1&e&&(d.Sb(0,"shared-form-dialog",0),d.ac("closeEvent",(function(){return t.onClose()}))("submitEvent",(function(){return t.onSubmit()})),d.Nb(1,"infL-main-form",1),d.Rb()),2&e&&(d.jc("disabled",t.loading||(null==t.mfc?null:t.mfc.mainForm.pristine)||!(null!=t.mfc&&t.mfc.mainForm.valid))("loading",t.loading)("title",t.title)("submitText",t.data.dictionary.translate("save"))("cancelText",t.data.dictionary.translate("cancel")),d.Bb(1),d.jc("data",t.data.rescue)("dictionary",t.data.dictionary))},directives:[r.f,k],styles:[""]}),e})();var P=i("ruxD"),q=i("DGrs"),Q=i("8j5Y"),A=i("J+dc"),G=i("J0Eg");let H=(()=>{class e extends G.a{constructor(e,t){super(),this.rs=e,this.datePipe=t}loadData(e){let t;return t={folio:null==e?void 0:e.folio,code:null==e?void 0:e.spCode,cliente:null==e?void 0:e.customer,del:"N"},this.rs.findByCriteria(t).pipe(Object(Q.a)(e=>{let t;for(let i=0;i<e.length;i++)t=new Date(e[i].fechaFalla),e[i].name=e[i].codes.providerName,e[i].distributor=e[i].codes.responsibleBranchCode,e[i].failureDate=this.datePipe.transform(t,"MM-dd-yyyy");this.dataSource.replaceAll(e)}),Object(A.a)(1))}push(e){this.dataSource.push(e)}}return e.\u0275fac=function(t){return new(t||e)(d.Wb(r.r),d.Wb(a.e))},e.\u0275prov=d.Ib({token:e,factory:e.\u0275fac,providedIn:"root"}),e})();var J=i("+Tre"),U=i("PBFl");function z(e,t){1&e&&d.Nb(0,"shared-progress-bar")}let V=(()=>{class e{constructor(e,t,i,a){this.mts=e,this.message=t,this.dialog=i,this.rs=a,this.columns=W,this.loading=!1,this.operations=Y,this.subscriptions=[],this.massLoadFile=[],this.eraseData=!1,this.massiveLoadErrors=[],this.eventListeners(),this.mts.loadData(null).subscribe(e=>{this.dataSource=e})}ngOnInit(){for(let e=0;e<this.columns.length;e++)if(this.columns[e].label=this.dictionary.translate(W[e].label),"operations"==this.columns[e].key)for(let t=0;t<this.columns[e].value.length;t++)this.columns[e].value[t].label=this.dictionary.translate(W[e].value[t].label)}ngOnDestroy(){this.subscriptions.forEach(e=>e.unsubscribe())}onOperation(e){switch(e.operation){case"edit":this.onEdit(e.item);break;case"delete":this.onDelete(e.item)}}massLoadFunction(){this.loading=!0,this.rs.massiveLoad(this.massLoadFile[0].data,1==this.eraseData?"1":"0").pipe(Object(P.a)(()=>{this.loading=!1,this.mts.loadData().subscribe(e=>{this.dataSource=e}),this.openResults()})).subscribe(e=>{const t=[];e.forEach(e=>{(null==e?void 0:e.errores)&&(t.push("Registros Actualizados: "+e.registrosActualizados,"Registros Eliminados: "+e.registrosEliminados,"Registros Nuevos: "+e.registrosNuevos),t.push(...e.errores.map(e=>`Renglon: ${e.line} - ${e.errorType}`)))}),this.massiveLoadErrors=t})}onAdd(){this.dialog.open(O,{disableClose:!0,height:"600px",width:"600px",data:{dictionary:this.dictionary,rescue:null}}).afterClosed().subscribe(e=>{e&&(this.mts.push(e),this.mts.loadData().subscribe(e=>{this.dataSource=e}),this.message.genericSaveMessage())})}onEdit(e){this.dialog.open(O,{disableClose:!0,height:"550px",width:"600px",data:{dictionary:this.dictionary,rescue:e}}).afterClosed().subscribe(e=>{e&&(this.message.genericSaveMessage(),this.mts.loadData(null).subscribe(e=>{this.dataSource=e}))})}onDelete(e){this.loading=!0,this.rs.update({action:"estatus",del:"Y"},e.folio.toUpperCase()).subscribe(e=>{this.message.show("Rescate Eliminado"),this.mts.loadData(null).subscribe(e=>{this.dataSource=e}),this.loading=!1},e=>{this.message.genericHttpError(),this.loading=!1})}eventListeners(){this.subscriptions.push(this.mts.dataEvent.subscribe(e=>this.operations.forEach(t=>t.disabled=0===e.length)))}openResults(){this.dialog.open(q.a,{disableClose:!1,height:"400px",width:"600px",data:{massiveLoadErrors:this.massiveLoadErrors}})}}return e.\u0275fac=function(t){return new(t||e)(d.Mb(H),d.Mb(r.k),d.Mb(c.b),d.Mb(r.r))},e.\u0275cmp=d.Gb({type:e,selectors:[["infL-main-table"]],inputs:{dictionary:"dictionary"},decls:15,vars:14,consts:[[4,"ngIf"],[1,"mat-elevation-z8","p1"],["fxLayout","row","fxLayout.xs","column"],["fxFlex","5%"],["fxFlex","20%"],["accept","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",3,"disabled","files","option","lengthFile"],["fxFlex","20%",2,"align-self","center"],[3,"ngModel","ngModelChange"],["fxFlex","50%",2,"align-self","center"],["mat-raised-button","",3,"disabled","click"],[3,"dictionary","columns","data","showPagination","operationEvent"],[3,"text","callback"]],template:function(e,t){1&e&&(d.Bc(0,z,1,0,"shared-progress-bar",0),d.Sb(1,"div",1),d.Sb(2,"div",2),d.Nb(3,"div",3),d.Sb(4,"div",4),d.Nb(5,"shared-file-upload-control",5),d.Rb(),d.Sb(6,"div",6),d.Sb(7,"mat-checkbox",7),d.ac("ngModelChange",(function(e){return t.eraseData=e})),d.Rb(),d.Dc(8),d.Rb(),d.Sb(9,"div",8),d.Sb(10,"button",9),d.ac("click",(function(){return t.massLoadFunction()})),d.Dc(11),d.Rb(),d.Rb(),d.Rb(),d.Rb(),d.Nb(12,"br"),d.Sb(13,"shared-search-table",10),d.ac("operationEvent",(function(e){return t.onOperation(e)})),d.Rb(),d.Sb(14,"shared-add-button",11),d.ac("callback",(function(){return t.onAdd()})),d.Rb()),2&e&&(d.jc("ngIf",t.loading),d.Bb(5),d.jc("disabled",!1)("files",t.massLoadFile)("option",t.dictionary.translate("fileUp"))("lengthFile",t.dictionary.translate("lengthFile")),d.Bb(2),d.jc("ngModel",t.eraseData),d.Bb(1),d.Fc(" ",t.dictionary.translate("eraseBefore")," "),d.Bb(2),d.jc("disabled",!t.massLoadFile[0]||t.loading),d.Bb(1),d.Fc(" ",t.dictionary.translate("massive")," "),d.Bb(2),d.jc("dictionary",t.dictionary)("columns",t.columns)("data",t.dataSource)("showPagination",!0),d.Bb(1),d.jc("text",t.dictionary.translate("addRescue")))},directives:[a.l,h.c,h.a,r.e,J.a,o.l,o.o,U.b,r.v,r.a,r.o],styles:[""]}),e})();const W=[{key:"spCode",label:"spCode"},{key:"name",label:"name"},{key:"folio",label:"folio"},{key:"failureDate",label:"failureDate"},{key:"cliente",label:"customer"},{key:"operations",label:"actions",type:r.b.Operations,value:[{label:"edit",matIcon:"edit",operation:"edit"},{label:"delete",matIcon:"delete",operation:"delete"}]}],Y=[{disabled:!1,faIcon:"faFileExcel",label:"Exportar Reporte",operation:"export"}];function $(e,t){1&e&&d.Nb(0,"shared-progress-bar")}function K(e,t){if(1&e&&(d.Sb(0,"div"),d.Bc(1,$,1,0,"shared-progress-bar",0),d.Nb(2,"infL-main-table",1),d.Rb()),2&e){const e=d.ec();d.Bb(1),d.jc("ngIf",e.loading),d.Bb(1),d.jc("dictionary",e.dictionary)}}let _=(()=>{class e{constructor(e,t,i,a,s){this.dialog=e,this.message=t,this.mts=i,this.rs=a,this.tls=s,this.dictionary=new r.j,this.fields=Z,this.loading=!1,this.massLoadFile=[],this.eraseData=!1,this.massiveLoadErrors=[],this.flag=!1}ngOnInit(){this.tls.getLenguaje().subscribe(e=>{this.dictionary.setLanguage(e.language),this.flag=!0})}massLoadFunction(){this.loading=!0,this.rs.massiveLoad(this.massLoadFile[0].data,1==this.eraseData?"1":"0").pipe(Object(P.a)(()=>{this.loading=!1,this.mts.loadData().subscribe(),this.openResults()})).subscribe(e=>{const t=[];e.forEach(e=>{(null==e?void 0:e.errores)&&(t.push("Registros Actualizados: "+e.registrosActualizados,"Registros Eliminados: "+e.registrosEliminados,"Registros Nuevos: "+e.registrosNuevos),t.push(...e.errores.map(e=>`Renglon: ${e.line} - ${e.errorType}`)))}),this.massiveLoadErrors=t})}onAdd(){this.dialog.open(O,{disableClose:!0,height:"600px",width:"600px",data:{dictionary:this.dictionary,rescue:null}}).afterClosed().subscribe(e=>{e&&(this.mts.push(e),this.mts.loadData().subscribe(),this.message.genericSaveMessage())})}onSearch(e){return e?(this.loading=!0,this.mts.loadData(e).subscribe(()=>{},e=>this.message.genericHttpError(e),()=>this.loading=!1)):this.mts.loadData(null).subscribe(),null}openResults(){this.dialog.open(q.a,{disableClose:!1,height:"400px",width:"600px",data:{massiveLoadErrors:this.massiveLoadErrors}})}}return e.\u0275fac=function(t){return new(t||e)(d.Mb(c.b),d.Mb(r.k),d.Mb(H),d.Mb(r.r),d.Mb(r.u))},e.\u0275cmp=d.Gb({type:e,selectors:[["infL-main-view"]],decls:1,vars:1,consts:[[4,"ngIf"],[3,"dictionary"]],template:function(e,t){1&e&&d.Bc(0,K,3,2,"div",0),2&e&&d.jc("ngIf",t.flag)},directives:[a.l,V,r.o],styles:[""]}),e})();const Z=[{label:"Folio",type:"text",value:"folio"},{label:"Cliente",type:"text",value:"customer"}],X=[{component:_,path:""}];let ee=(()=>{class e{}return e.\u0275mod=d.Kb({type:e}),e.\u0275inj=d.Jb({factory:function(t){return new(t||e)},imports:[[l.a.forChild(X)],l.a]}),e})();var te=i("R7+U"),ie=i("KZIX"),ae=i("FlRo"),se=i("cePI"),oe=i("iAde"),re=i("k8N0"),ne=i("bFHC"),le=i("Jb3d"),ce=i("66mq"),de=i("csyo");let be=(()=>{class e{}return e.\u0275mod=d.Kb({type:e}),e.\u0275inj=d.Jb({factory:function(t){return new(t||e)},imports:[[a.c,ee,s.a,o.g,v.c,p.e,f.c,y.m,te.b,ie.a,ae.m,o.q,se.c,oe.b,re.a,r.t,n.a,U.c,c.e,ne.b,le.b,ce.b,de.b,g.b,J.b]]}),e})()}}]);