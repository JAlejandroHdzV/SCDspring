(window.webpackJsonp=window.webpackJsonp||[]).push([[8],{Cmn3:function(t,e,a){"use strict";a.r(e),a.d(e,"GuaranteeModule",(function(){return ft}));var i=a("2kYt"),s=a("40Bj"),n=a("nIj0"),o=a("m1yc"),r=a("PCNd"),l=a("sEIs"),c=a("EM62"),d=a("OZ4H"),b=a("8j5Y"),u=a("J+dc"),m=a("J0Eg");let h=(()=>{class t extends m.a{constructor(t,e){super(),this.gs=t,this.datePipe=e}loadData(t){let e;return e={folio:null==t?void 0:t.folio,spCode:null==t?void 0:t.spcode,montoReclamado:null==t?void 0:t.reclaimedAmount,status:null==t?void 0:t.status,del:"N"},this.gs.findByCriteria(e).pipe(Object(b.a)(t=>{var e;let a;for(let i=0;i<t.length;i++)a=new Date(t[i].fechaReclamo),t[i].dealerName=null===(e=t[i].codes)||void 0===e?void 0:e.providerName,t[i].statusStr=t[i].statusGar.status,t[i].reclaimedAmount=t[i].montoReclamado,t[i].claimDate=this.datePipe.transform(a,"MM-dd-yyyy");this.dataSource.replaceAll(t)}),Object(u.a)(1))}push(t){this.dataSource.push(t)}}return t.\u0275fac=function(e){return new(e||t)(c.Wb(o.h),c.Wb(i.e))},t.\u0275prov=c.Ib({token:t,factory:t.\u0275fac,providedIn:"root"}),t})();var p=a("jIqt"),f=a("YtkY"),g=a("mWib"),y=a("ruxD"),S=a("qFEQ"),v=a("29Wa"),D=a("Cd2c"),R=a("R7+U"),B=a("ulve"),C=a("gcUQ"),F=a("mFH5"),j=a("csyo");function N(t,e){if(1&t&&(c.Qb(0),c.Dc(1),c.Pb()),2&t){const t=c.ec();c.Bb(1),c.Ec(t.dictionary.translate("mandatoryField"))}}function I(t,e){if(1&t&&(c.Qb(0),c.Dc(1),c.Pb()),2&t){const t=c.ec();c.Bb(1),c.Ec(t.dictionary.translate("maxOf")+"20 "+t.dictionary.translate("characters"))}}function x(t,e){if(1&t&&(c.Qb(0),c.Dc(1),c.Pb()),2&t){const t=c.ec();c.Bb(1),c.Ec(t.dictionary.translate("duplicated")+" "+t.dictionary.translate("guarantee"))}}function A(t,e){if(1&t&&(c.Sb(0,"mat-option",27),c.Dc(1),c.Rb()),2&t){const t=e.$implicit;c.jc("value",t.value),c.Bb(1),c.Fc(" ",t.label," ")}}function O(t,e){if(1&t){const t=c.Tb();c.Sb(0,"mat-option",28),c.ac("onSelectionChange",(function(e){return c.sc(t),c.ec().spCodeChanged(e)})),c.Dc(1),c.Rb()}if(2&t){const t=e.$implicit;c.jc("value",t),c.Bb(1),c.Fc(" ",t.spCode," ")}}function k(t,e){1&t&&c.Nb(0,"mat-spinner",29),2&t&&c.jc("diameter",20)}function E(t,e){if(1&t&&(c.Sb(0,"mat-option",27),c.Dc(1),c.Rb()),2&t){const t=e.$implicit;c.jc("value",t.value),c.Bb(1),c.Fc(" ",t.label," ")}}function L(t,e){if(1&t&&(c.Qb(0),c.Dc(1),c.Pb()),2&t){const t=c.ec();c.Bb(1),c.Ec(t.dictionary.translate("mandatoryField"))}}function w(t,e){if(1&t&&(c.Qb(0),c.Dc(1),c.Pb()),2&t){const t=c.ec();c.Bb(1),c.Ec(t.dictionary.translate("maxOf")+"10 "+t.dictionary.translate("characters"))}}function P(t,e){if(1&t&&(c.Qb(0),c.Dc(1),c.Pb()),2&t){const t=c.ec();c.Bb(1),c.Ec(t.dictionary.translate("mandatoryField"))}}function V(t,e){if(1&t&&(c.Qb(0),c.Dc(1),c.Pb()),2&t){const t=c.ec();c.Bb(1),c.Ec(t.dictionary.translate("maxOf")+"10 "+t.dictionary.translate("characters"))}}function T(t,e){if(1&t&&(c.Qb(0),c.Dc(1),c.Pb()),2&t){const t=c.ec(2);c.Bb(1),c.Ec(t.dictionary.translate("mandatoryField"))}}function q(t,e){if(1&t&&(c.Qb(0),c.Dc(1),c.Pb()),2&t){const t=c.ec(2);c.Bb(1),c.Ec(t.dictionary.translate("maxOf")+"10 "+t.dictionary.translate("characters"))}}function M(t,e){if(1&t&&(c.Sb(0,"mat-form-field"),c.Nb(1,"input",30),c.Sb(2,"mat-error"),c.Bc(3,T,2,1,"ng-container",4),c.Bc(4,q,2,1,"ng-container",4),c.Rb(),c.Rb()),2&t){const t=c.ec();c.Bb(1),c.jc("placeholder",t.dictionary.translate("paidMount")+" *"),c.Bb(2),c.jc("ngIf",t.payedAmount.invalid&&t.payedAmount.errors.required),c.Bb(1),c.jc("ngIf",t.payedAmount.invalid&&t.payedAmount.errors.maxlength)}}function Q(t,e){if(1&t&&(c.Sb(0,"mat-form-field"),c.Nb(1,"input",31),c.Nb(2,"mat-datepicker-toggle",20),c.Nb(3,"mat-datepicker",null,32),c.Sb(5,"mat-error"),c.Dc(6),c.Rb(),c.Rb()),2&t){const t=c.qc(4),e=c.ec();c.Bb(1),c.jc("matDatepicker",t)("placeholder",e.dictionary.translate("rejectedDate")+" *"),c.Bb(1),c.jc("for",t),c.Bb(4),c.Fc(" ",e.dictionary.translate("mandatoryField")," ")}}function G(t,e){if(1&t&&(c.Qb(0),c.Dc(1),c.Pb()),2&t){const t=c.ec(2);c.Bb(1),c.Ec(t.dictionary.translate("mandatoryField"))}}function U(t,e){if(1&t&&(c.Qb(0),c.Dc(1),c.Pb()),2&t){const t=c.ec(2);c.Bb(1),c.Ec(t.dictionary.translate("maxOf")+"20 "+t.dictionary.translate("characters"))}}function W(t,e){if(1&t&&(c.Sb(0,"div",1),c.Sb(1,"div",2),c.Sb(2,"mat-form-field"),c.Nb(3,"input",33),c.Nb(4,"mat-datepicker-toggle",20),c.Nb(5,"mat-datepicker",null,34),c.Sb(7,"mat-error"),c.Dc(8),c.Rb(),c.Rb(),c.Rb(),c.Sb(9,"div",2),c.Sb(10,"mat-form-field"),c.Nb(11,"input",35),c.Sb(12,"mat-error"),c.Bc(13,G,2,1,"ng-container",4),c.Bc(14,U,2,1,"ng-container",4),c.Rb(),c.Rb(),c.Rb(),c.Rb()),2&t){const t=c.qc(6),e=c.ec();c.Bb(3),c.jc("matDatepicker",t)("placeholder",e.dictionary.translate("solutionDate")+" *"),c.Bb(1),c.jc("for",t),c.Bb(4),c.Fc(" ",e.dictionary.translate("mandatoryField")," "),c.Bb(3),c.jc("placeholder",e.dictionary.translate("reparationDays")+" *"),c.Bb(2),c.jc("ngIf",e.reparationDays.invalid&&e.reparationDays.errors.required),c.Bb(1),c.jc("ngIf",e.reparationDays.invalid&&e.reparationDays.errors.maxlength)}}function z(t,e){if(1&t&&(c.Qb(0),c.Dc(1),c.Pb()),2&t){const t=c.ec(2);c.Bb(1),c.Ec(t.dictionary.translate("mandatoryField"))}}function H(t,e){if(1&t&&(c.Sb(0,"div",1),c.Sb(1,"div",2),c.Sb(2,"mat-form-field"),c.Nb(3,"input",36),c.Sb(4,"mat-error"),c.Bc(5,z,2,1,"ng-container",4),c.Rb(),c.Rb(),c.Rb(),c.Rb()),2&t){const t=c.ec();c.Bb(3),c.jc("placeholder","Total SRTs *"),c.Bb(2),c.jc("ngIf",t.TotalSRTs.invalid&&t.TotalSRTs.errors.required)}}let J=(()=>{class t{constructor(t,e,a,i,s){this.fb=t,this.fs=e,this.sps=a,this.gs=i,this.changeDetectorRef=s,this.cummins=window.cummins,this.validDates=!0,this.disabledEvent=new c.o,this.spCodesOptions=[],this.statusOptions=[],this.ccOptions=[]}get folio(){return this.mainForm.get("folio")}get status(){return this.mainForm.get("status")}get spCode(){return this.mainForm.get("spCode")}get nameSPCode(){return this.mainForm.get("nameSPCode")}get distributorSPCode(){return this.mainForm.get("distributorSPCode")}get cc(){return this.mainForm.get("cc")}get esn(){return this.mainForm.get("esn")}get reclaimedAmount(){return this.mainForm.get("reclaimedAmount")}get payedAmount(){return this.mainForm.get("payedAmount")}get claimDate(){return this.mainForm.get("claimDate")}get failureDate(){return this.mainForm.get("failureDate")}get finishReparationDate(){return this.mainForm.get("finishReparationDate")}get rejectedDate(){return this.mainForm.get("rejectedDate")}get solutionDate(){return this.mainForm.get("solutionDate")}get reparationDays(){return this.mainForm.get("reparationDays")}get TotalSRTs(){return this.mainForm.get("TotalSRTs")}ngOnInit(){this.spCodeLoading=!0,this.isNew=!this.data,this.setupForm(),this.spCode.disable(),this.sps.getSpCodes().subscribe(t=>{this.spCodesOptions=[...t],this.spCodeLoading=!1,this.spCode.enable(),this.filteredOptions=this.mainForm.controls.spCode.valueChanges.pipe(Object(p.a)(""),Object(f.a)(t=>"string"==typeof t?t:t.spCode),Object(f.a)(t=>t?this._filter(t):this.spCodesOptions.slice()))}),this.gs.bringStatus().subscribe(t=>{this.statusOptions=t}),this.gs.bringCC().subscribe(t=>{this.ccOptions=t}),this.status.valueChanges.subscribe(t=>{1==t?(this.payedAmount.setValidators([n.s.required,n.s.min(1)]),this.payedAmount.updateValueAndValidity(),this.solutionDate.setValidators([n.s.required]),this.solutionDate.updateValueAndValidity(),this.reparationDays.setValidators([n.s.required,n.s.min(0)]),this.reparationDays.updateValueAndValidity(),this.TotalSRTs.setValidators([n.s.required,n.s.min(1)]),this.TotalSRTs.updateValueAndValidity(),this.rejectedDate.setValidators(null),this.rejectedDate.updateValueAndValidity()):(this.payedAmount.setValidators(null),this.payedAmount.updateValueAndValidity(),this.solutionDate.setValidators(null),this.solutionDate.updateValueAndValidity(),this.reparationDays.setValidators(null),this.reparationDays.updateValueAndValidity(),this.TotalSRTs.setValidators(null),this.TotalSRTs.updateValueAndValidity(),this.rejectedDate.setValidators([n.s.required]),this.rejectedDate.updateValueAndValidity())}),this.filteredOptions=this.spCode.valueChanges.pipe(Object(g.a)(500),Object(f.a)(t=>this._filter(t)))}disableFields(){this.fs.disableFields(this.mainForm)}enableFields(){this.fs.enableFields(this.mainForm)}submit(){const t=this.mainForm.value;let e;return e={folio:t.folio.toUpperCase(),spCode:t.spCode.spCode,codigoCta:t.cc,esn:t.esn.toUpperCase(),montoReclamado:t.reclaimedAmount,montoPagado:1==t.status?t.payedAmount:null,fechaReclamo:t.claimDate.toISOString(),fechaFalla:t.failureDate.toISOString(),fechaRechazo:2==t.status?t.rejectedDate.toISOString():null,fechaSolucion:1==t.status?t.solutionDate.toISOString():null,diasReparacion:1==t.status?t.reparationDays:null,fechaFinReparacion:t.finishReparationDate.toISOString(),status:t.status,total:1==t.status?t.TotalSRTs:null,del:"N"},this.isNew?this.gs.save(e):this.gs.update(e)}displayFn(t){return t&&t.spCode?t.spCode:""}_filter(t){const e=t.toLowerCase();return this.spCodesOptions.filter(t=>t.spCode.toLowerCase().includes(e))}spCodeChanged(t){const e=t.source.value;this.nameSPCode.setValue(e.providerName),this.distributorSPCode.setValue(e.responsibleBranchCode+" - "+e.responsibleBranchName)}setupForm(){this.mainForm=this.fb.group({folio:[null,[n.s.required,n.s.maxLength(20)],this.duplicateValidator.bind(this)],status:[null,[n.s.required]],spCode:[null,[n.s.required,n.s.maxLength(200)]],nameSPCode:[null,[n.s.required]],distributorSPCode:[null,[n.s.required]],cc:[null,[n.s.required,n.s.maxLength(250)]],esn:[null,[n.s.required,n.s.maxLength(10),n.s.min(1)]],reclaimedAmount:[null,[n.s.required,n.s.maxLength(250),n.s.min(1)]],payedAmount:[null],claimDate:[null,[n.s.required,n.s.maxLength(250)]],failureDate:[null,[n.s.required,n.s.maxLength(250)]],finishReparationDate:[null,[n.s.required,n.s.maxLength(250)]],rejectedDate:[null],solutionDate:[null],reparationDays:[null],TotalSRTs:[null],del:null}),this.isNew||(this.sps.findByCriteria({spCode:this.data.codes.responsibleBranchCode,providerName:null,responsibleBranchCode:null,idPais:null,idOem:null,add:null,city:null,iso:null,tipo:null,creationDate:null,createdBy:null,lastUpdateDate:null,lastUpdatedBy:null,del:"N"}).subscribe(t=>{this.mainForm.patchValue({folio:this.data.folio,status:this.data.statusGar.idstatus,cc:this.data.codigoCta,esn:this.data.esn,reclaimedAmount:this.data.montoReclamado,payedAmount:this.data.montoPagado,claimDate:new Date(this.data.fechaReclamo),failureDate:new Date(this.data.fechaFalla),finishReparationDate:new Date(this.data.fechaFinReparacion),rejectedDate:this.data.fechaRechazo?new Date(this.data.fechaRechazo):null,solutionDate:this.data.fechaSolucion?new Date(this.data.fechaSolucion):null,reparationDays:this.data.diasReparacion,TotalSRTs:this.data.total,del:this.data.delete}),this.sps.getInfoSpcode(this.data.codes.spCode).subscribe(t=>{this.mainForm.patchValue({spCode:t[0],nameSPCode:t[0].providerName,distributorSPCode:t[0].responsibleBranchCode+" - "+t[0].responsibleBranchName})})}),this.folio.disable())}duplicateValidator(t){const e={folio:t.value.toUpperCase(),del:"N"};return this.gs.findByCriteria(e).pipe(Object(f.a)(t=>0===t.length||1==t.length&&!this.isNew?null:{duplicated:!0}),Object(y.a)(()=>this.changeDetectorRef.markForCheck()))}}return t.\u0275fac=function(e){return new(e||t)(c.Mb(n.d),c.Mb(o.g),c.Mb(o.s),c.Mb(o.h),c.Mb(c.h))},t.\u0275cmp=c.Gb({type:t,selectors:[["infL-main-form"]],inputs:{data:"data",dictionary:"dictionary"},decls:89,vars:43,consts:[["autocomplete","off",3,"formGroup"],["fxLayout","row","fxLayout.xs","column"],["fxFlex","50%"],["formControlName","folio","matInput","",3,"placeholder"],[4,"ngIf"],["formControlName","status"],[3,"value",4,"ngFor","ngForOf"],["fxFlex","100%"],[1,"example-full-width"],["formControlName","spCode","matInput","","required","","type","text",3,"matAutocomplete","placeholder"],[3,"displayWith"],["auto","matAutocomplete"],[3,"value","onSelectionChange",4,"ngFor","ngForOf"],["strokeWidth","3",3,"diameter",4,"ngIf"],["formControlName","nameSPCode","matInput","","readonly","",3,"placeholder"],["formControlName","distributorSPCode","matInput","","readonly","",3,"placeholder"],["formControlName","cc"],["formControlName","esn","matInput","","maxlength","10",3,"placeholder"],["formControlName","reclaimedAmount","matInput","","type","number",3,"placeholder"],["formControlName","claimDate","matInput","","readonly","",3,"matDatepicker","placeholder"],["matSuffix","",3,"for"],["claimDate",""],["formControlName","failureDate","matInput","","readonly","",3,"matDatepicker","placeholder"],["failureDate",""],["formControlName","finishReparationDate","matInput","","readonly","",3,"matDatepicker","placeholder"],["finishReparationDate",""],["fxLayout","row","fxLayout.xs","column",4,"ngIf"],[3,"value"],[3,"value","onSelectionChange"],["strokeWidth","3",3,"diameter"],["formControlName","payedAmount","matInput","","type","number",3,"placeholder"],["formControlName","rejectedDate","matInput","","readonly","",3,"matDatepicker","placeholder"],["rejectedDate",""],["formControlName","solutionDate","matInput","","readonly","",3,"matDatepicker","placeholder"],["solutionDate",""],["formControlName","reparationDays","matInput","","type","number",3,"placeholder"],["formControlName","TotalSRTs","matInput","","type","number",3,"placeholder"]],template:function(t,e){if(1&t&&(c.Sb(0,"form",0),c.Sb(1,"div",1),c.Sb(2,"div",2),c.Sb(3,"mat-form-field"),c.Nb(4,"input",3),c.Sb(5,"mat-error"),c.Bc(6,N,2,1,"ng-container",4),c.Bc(7,I,2,1,"ng-container",4),c.Bc(8,x,2,1,"ng-container",4),c.Rb(),c.Rb(),c.Rb(),c.Sb(9,"div",2),c.Sb(10,"mat-form-field"),c.Sb(11,"mat-label"),c.Dc(12),c.Rb(),c.Sb(13,"mat-select",5),c.Bc(14,A,2,2,"mat-option",6),c.Rb(),c.Sb(15,"mat-error"),c.Dc(16),c.Rb(),c.Rb(),c.Rb(),c.Rb(),c.Sb(17,"div",1),c.Sb(18,"div",7),c.Sb(19,"mat-form-field",8),c.Nb(20,"input",9),c.Sb(21,"mat-autocomplete",10,11),c.Bc(23,O,2,2,"mat-option",12),c.fc(24,"async"),c.Rb(),c.Sb(25,"mat-error"),c.Dc(26),c.Rb(),c.Bc(27,k,1,1,"mat-spinner",13),c.Rb(),c.Rb(),c.Rb(),c.Sb(28,"div",1),c.Sb(29,"div",2),c.Sb(30,"mat-form-field"),c.Nb(31,"input",14),c.Rb(),c.Rb(),c.Sb(32,"div",2),c.Sb(33,"mat-form-field"),c.Nb(34,"input",15),c.Rb(),c.Rb(),c.Rb(),c.Sb(35,"div",1),c.Sb(36,"div",2),c.Sb(37,"mat-form-field"),c.Sb(38,"mat-label"),c.Dc(39),c.Rb(),c.Sb(40,"mat-select",16),c.Bc(41,E,2,2,"mat-option",6),c.Rb(),c.Sb(42,"mat-error"),c.Dc(43),c.Rb(),c.Rb(),c.Rb(),c.Sb(44,"div",2),c.Sb(45,"mat-form-field"),c.Nb(46,"input",17),c.Sb(47,"mat-error"),c.Bc(48,L,2,1,"ng-container",4),c.Bc(49,w,2,1,"ng-container",4),c.Rb(),c.Rb(),c.Rb(),c.Rb(),c.Sb(50,"div",1),c.Sb(51,"div",2),c.Sb(52,"mat-form-field"),c.Nb(53,"input",18),c.Sb(54,"mat-error"),c.Bc(55,P,2,1,"ng-container",4),c.Bc(56,V,2,1,"ng-container",4),c.Rb(),c.Rb(),c.Rb(),c.Sb(57,"div",2),c.Bc(58,M,5,3,"mat-form-field",4),c.Rb(),c.Rb(),c.Sb(59,"div",1),c.Sb(60,"div",2),c.Sb(61,"mat-form-field"),c.Nb(62,"input",19),c.Nb(63,"mat-datepicker-toggle",20),c.Nb(64,"mat-datepicker",null,21),c.Sb(66,"mat-error"),c.Dc(67),c.Rb(),c.Rb(),c.Rb(),c.Sb(68,"div",2),c.Sb(69,"mat-form-field"),c.Nb(70,"input",22),c.Nb(71,"mat-datepicker-toggle",20),c.Nb(72,"mat-datepicker",null,23),c.Sb(74,"mat-error"),c.Dc(75),c.Rb(),c.Rb(),c.Rb(),c.Rb(),c.Sb(76,"div",1),c.Sb(77,"div",2),c.Sb(78,"mat-form-field"),c.Nb(79,"input",24),c.Nb(80,"mat-datepicker-toggle",20),c.Nb(81,"mat-datepicker",null,25),c.Sb(83,"mat-error"),c.Dc(84),c.Rb(),c.Rb(),c.Rb(),c.Sb(85,"div",2),c.Bc(86,Q,7,4,"mat-form-field",4),c.Rb(),c.Rb(),c.Bc(87,W,15,7,"div",26),c.Bc(88,H,6,2,"div",26),c.Rb()),2&t){const t=c.qc(22),a=c.qc(65),i=c.qc(73),s=c.qc(82);c.jc("formGroup",e.mainForm),c.Bb(4),c.jc("placeholder",e.dictionary.translate("folio")+" *"),c.Bb(2),c.jc("ngIf",e.folio.invalid&&e.folio.errors.required),c.Bb(1),c.jc("ngIf",e.folio.invalid&&e.folio.errors.maxlength),c.Bb(1),c.jc("ngIf",e.folio.invalid&&e.folio.errors.duplicated),c.Bb(4),c.Ec(e.dictionary.translate("status")+"*"),c.Bb(2),c.jc("ngForOf",e.statusOptions),c.Bb(2),c.Fc(" ",e.dictionary.translate("mandatoryField")," "),c.Bb(4),c.jc("matAutocomplete",t)("placeholder",e.dictionary.translate("spCode")),c.Bb(1),c.jc("displayWith",e.displayFn),c.Bb(2),c.jc("ngForOf",c.gc(24,41,e.filteredOptions)),c.Bb(3),c.Fc(" ",e.dictionary.translate("mandatoryField")," "),c.Bb(1),c.jc("ngIf",e.spCodeLoading),c.Bb(4),c.jc("placeholder",e.dictionary.translate("name")),c.Bb(3),c.jc("placeholder",e.dictionary.translate("distributor")),c.Bb(5),c.Ec(e.dictionary.translate("CC")+"*"),c.Bb(2),c.jc("ngForOf",e.ccOptions),c.Bb(2),c.Fc(" ",e.dictionary.translate("mandatoryField")," "),c.Bb(3),c.jc("placeholder","ESN *"),c.Bb(2),c.jc("ngIf",e.esn.invalid&&e.esn.errors.required),c.Bb(1),c.jc("ngIf",e.esn.invalid&&e.esn.errors.maxlength),c.Bb(4),c.jc("placeholder",e.dictionary.translate("reclaimedAmount")+" *"),c.Bb(2),c.jc("ngIf",e.reclaimedAmount.invalid&&e.reclaimedAmount.errors.required),c.Bb(1),c.jc("ngIf",e.reclaimedAmount.invalid&&e.reclaimedAmount.errors.maxlength),c.Bb(2),c.jc("ngIf",1==e.status.value),c.Bb(4),c.jc("matDatepicker",a)("placeholder",e.dictionary.translate("claimDate")+" *"),c.Bb(1),c.jc("for",a),c.Bb(4),c.Fc(" ",e.dictionary.translate("mandatoryField")," "),c.Bb(3),c.jc("matDatepicker",i)("placeholder",e.dictionary.translate("failureDate")+" *"),c.Bb(1),c.jc("for",i),c.Bb(4),c.Fc(" ",e.dictionary.translate("mandatoryField")," "),c.Bb(4),c.jc("matDatepicker",s)("placeholder",e.dictionary.translate("finishReparationDate")+" *"),c.Bb(1),c.jc("for",s),c.Bb(4),c.Fc(" ",e.dictionary.translate("mandatoryField")," "),c.Bb(2),c.jc("ngIf",2==e.status.value),c.Bb(1),c.jc("ngIf",1==e.status.value),c.Bb(1),c.jc("ngIf",1==e.status.value)}},directives:[n.t,n.m,n.f,S.c,S.a,v.c,n.c,D.b,n.l,n.e,v.b,i.l,v.f,R.a,i.k,B.c,n.r,B.a,n.h,n.p,C.b,C.d,v.g,C.a,F.n,j.c],pipes:[i.b],styles:[".mat-form-field[_ngcontent-%COMP%]{min-inline-size:95%}"]}),t})(),$=(()=>{class t{constructor(t,e,a){this.data=t,this.dialogRef=e,this.message=a,this.loading=!1}ngOnInit(){this.title=this.data.dictionary.translate(this.data.guarantee?"editGuarantee":"addGuarantee")}onClose(){this.dialogRef.close()}onSubmit(){console.log("onSubmit"),this.loading=!0,this.mfc.disableFields(),this.mfc.submit().subscribe(t=>{this.loading=!1,this.mfc.enableFields(),this.dialogRef.close({})},t=>{this.loading=!1,this.mfc.enableFields(),this.message.genericHttpError()})}}return t.\u0275fac=function(e){return new(e||t)(c.Mb(d.a),c.Mb(d.f),c.Mb(o.k))},t.\u0275cmp=c.Gb({type:t,selectors:[["infL-main-form-dialog"]],viewQuery:function(t,e){var a;1&t&&c.Hc(J,!0),2&t&&c.pc(a=c.bc())&&(e.mfc=a.first)},decls:2,vars:7,consts:[[1,"",3,"disabled","loading","title","submitText","cancelText","closeEvent","submitEvent"],[2,"width","200px",3,"data","dictionary"]],template:function(t,e){1&t&&(c.Sb(0,"shared-form-dialog",0),c.ac("closeEvent",(function(){return e.onClose()}))("submitEvent",(function(){return e.onSubmit()})),c.Nb(1,"infL-main-form",1),c.Rb()),2&t&&(c.jc("disabled",e.loading||(null==e.mfc?null:e.mfc.mainForm.pristine)||!(null!=e.mfc&&e.mfc.mainForm.valid))("loading",e.loading)("title",e.title)("submitText",e.data.dictionary.translate("save"))("cancelText",e.data.dictionary.translate("cancel")),c.Bb(1),c.jc("data",e.data.guarantee)("dictionary",e.data.dictionary))},directives:[o.f,J],styles:[""]}),t})();var Y=a("DGrs"),_=a("+Tre"),K=a("PBFl");function Z(t,e){1&t&&c.Nb(0,"shared-progress-bar")}let X=(()=>{class t{constructor(t,e,a,i){this.mts=t,this.message=e,this.dialog=a,this.gs=i,this.columns=tt,this.loading=!1,this.operations=et,this.subscriptions=[],this.massLoadFile=[],this.eraseData=!1,this.massiveLoadErrors=[],this.eventListeners(),this.mts.loadData(null).subscribe(t=>{this.dataSource=t})}ngOnInit(){for(let t=0;t<this.columns.length;t++)if(this.columns[t].label=this.dictionary.translate(tt[t].label),"operations"==this.columns[t].label)for(let e=0;e<this.columns[t].value.length;e++)this.columns[t].value[e].label=this.dictionary.translate(tt[t].value[e].label)}ngOnDestroy(){this.subscriptions.forEach(t=>t.unsubscribe())}onOperation(t){switch(t.operation){case"edit":this.onEdit(t.item);break;case"delete":this.onDelete(t.item)}}massLoadFunction(){this.loading=!0,this.gs.massiveLoad(this.massLoadFile[0].data,1==this.eraseData?"1":"0").pipe(Object(y.a)(()=>{this.loading=!1,this.mts.loadData(null).subscribe(t=>{this.dataSource=t}),this.openResults()})).subscribe(t=>{const e=[];t.forEach(t=>{(null==t?void 0:t.errores)&&(e.push("Registros Actualizados: "+t.registrosActualizados,"Registros Eliminados: "+t.registrosEliminados,"Registros Nuevos: "+t.registrosNuevos),e.push(...t.errores.map(t=>`Renglon: ${t.line} - ${t.errorType}`)))}),this.massiveLoadErrors=e})}onAdd(){this.dialog.open($,{disableClose:!0,height:"550px",width:"600px",data:{dictionary:this.dictionary,guarantee:null}}).afterClosed().subscribe(t=>{t?(this.mts.push(t),this.mts.loadData(null).subscribe(t=>{this.dataSource=t}),this.message.genericSaveMessage()):this.message.genericHttpError()})}onEdit(t){this.dialog.open($,{disableClose:!0,height:"550px",width:"600px",data:{dictionary:this.dictionary,guarantee:t}}).afterClosed().subscribe(t=>{t&&(this.message.genericSaveMessage(),this.mts.loadData(null).subscribe(t=>{this.dataSource=t}))})}onDelete(t){this.loading=!0,this.gs.delete(t.folio).subscribe(t=>{this.message.show(this.dictionary.translate("dataDeleted")),this.mts.loadData(null).subscribe(t=>{this.dataSource=t}),this.loading=!1},t=>{this.message.genericHttpError(),this.loading=!1})}eventListeners(){this.subscriptions.push(this.mts.dataEvent.subscribe(t=>this.operations.forEach(e=>e.disabled=0===t.length)))}openResults(){this.dialog.open(Y.a,{disableClose:!1,height:"400px",width:"600px",data:{massiveLoadErrors:this.massiveLoadErrors}})}}return t.\u0275fac=function(e){return new(e||t)(c.Mb(h),c.Mb(o.k),c.Mb(d.b),c.Mb(o.h))},t.\u0275cmp=c.Gb({type:t,selectors:[["infL-main-table"]],inputs:{dictionary:"dictionary"},decls:14,vars:14,consts:[[4,"ngIf"],[1,"mat-elevation-z8","p1"],["fxLayout","row","fxLayout.xs","column","fxLayoutAlign","space-around center"],["accept","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",3,"disabled","files","option","lengthFile"],[3,"ngModel","ngModelChange"],["mat-raised-button","",3,"disabled","click"],[3,"dictionary","columns","data","showPagination","operationEvent"],[3,"text","callback"]],template:function(t,e){1&t&&(c.Bc(0,Z,1,0,"shared-progress-bar",0),c.Sb(1,"div",1),c.Sb(2,"div",2),c.Sb(3,"div"),c.Nb(4,"shared-file-upload-control",3),c.Rb(),c.Sb(5,"div"),c.Sb(6,"mat-checkbox",4),c.ac("ngModelChange",(function(t){return e.eraseData=t})),c.Dc(7),c.Rb(),c.Rb(),c.Sb(8,"div"),c.Sb(9,"button",5),c.ac("click",(function(){return e.massLoadFunction()})),c.Dc(10),c.Rb(),c.Rb(),c.Rb(),c.Rb(),c.Nb(11,"br"),c.Sb(12,"shared-search-table",6),c.ac("operationEvent",(function(t){return e.onOperation(t)})),c.Rb(),c.Sb(13,"shared-add-button",7),c.ac("callback",(function(){return e.onAdd()})),c.Rb()),2&t&&(c.jc("ngIf",e.loading),c.Bb(4),c.jc("disabled",!1)("files",e.massLoadFile)("option",e.dictionary.translate("fileUp"))("lengthFile",e.dictionary.translate("lengthFile")),c.Bb(2),c.jc("ngModel",e.eraseData),c.Bb(1),c.Fc(" ",e.dictionary.translate("eraseBefore")," "),c.Bb(2),c.jc("disabled",!e.massLoadFile[0]||e.loading),c.Bb(1),c.Fc(" ",e.dictionary.translate("bulkLoad")," "),c.Bb(2),c.jc("dictionary",e.dictionary)("columns",e.columns)("data",e.dataSource)("showPagination",!0),c.Bb(1),c.jc("text",e.dictionary.translate("addGuarantee")))},directives:[i.l,S.c,S.b,o.e,_.a,n.l,n.o,K.b,o.v,o.a,o.o],styles:[""]}),t})();const tt=[{key:"folio",label:"folio"},{key:"spCode",label:"spCode"},{key:"dealerName",label:"name"},{key:"statusStr",label:"status"},{key:"reclaimedAmount",label:"reclaimedAmount"},{key:"claimDate",label:"claimDate"},{key:"operations",label:"actions",type:o.b.Operations,value:[{label:"edit",matIcon:"edit",operation:"edit"},{label:"delete",matIcon:"delete",operation:"delete"}]}],et=[{disabled:!1,faIcon:"faFileExcel",label:"Exportar Reporte",operation:"export"}];function at(t,e){1&t&&c.Nb(0,"shared-progress-bar")}function it(t,e){if(1&t&&(c.Sb(0,"div"),c.Nb(1,"infL-main-table",1),c.Rb()),2&t){const t=c.ec();c.Bb(1),c.jc("dictionary",t.dictionary)}}let st=(()=>{class t{constructor(t,e,a,i,s,n){this.dialog=t,this.message=e,this.mts=a,this.sps=i,this.gs=s,this.tls=n,this.dictionary=new o.j,this.fields=nt,this.loading=!1,this.massLoadFile=[],this.eraseData=!1,this.massiveLoadErrors=[],this.flag=!1}ngOnInit(){this.tls.getLenguaje().subscribe(t=>{this.dictionary.setLanguage(t.language),this.flag=!0}),this.gs.bringStatus().subscribe(t=>{this.fields[2].options=t}),this.sps.bringTheActivesSPCodes().subscribe(t=>{this.fields[1].options=t})}}return t.\u0275fac=function(e){return new(e||t)(c.Mb(d.b),c.Mb(o.k),c.Mb(h),c.Mb(o.s),c.Mb(o.h),c.Mb(o.u))},t.\u0275cmp=c.Gb({type:t,selectors:[["infL-main-view"]],decls:3,vars:2,consts:[[4,"ngIf"],[3,"dictionary"]],template:function(t,e){1&t&&(c.Bc(0,at,1,0,"shared-progress-bar",0),c.Nb(1,"br"),c.Bc(2,it,2,1,"div",0)),2&t&&(c.jc("ngIf",e.loading),c.Bb(2),c.jc("ngIf",e.flag))},directives:[i.l,o.o,X],styles:[""]}),t})();const nt=[{label:"Folio",type:"text",value:"folio"},{label:"SP Code",options:[],type:"select",value:"spcode"},{label:"Status",options:[],type:"select",value:"status"},{label:"Monto Reclamado",type:"text",value:"reclaimedAmount"}],ot=[{component:st,path:""}];let rt=(()=>{class t{}return t.\u0275mod=c.Kb({type:t}),t.\u0275inj=c.Jb({factory:function(e){return new(e||t)},imports:[[l.a.forChild(ot)],l.a]}),t})();var lt=a("KZIX"),ct=a("FlRo"),dt=a("cePI"),bt=a("iAde"),ut=a("k8N0"),mt=a("bFHC"),ht=a("Jb3d"),pt=a("66mq");let ft=(()=>{class t{}return t.\u0275mod=c.Kb({type:t}),t.\u0275inj=c.Jb({factory:function(e){return new(e||t)},imports:[[i.c,rt,s.a,n.g,C.c,v.e,D.c,F.m,R.b,lt.a,ct.m,n.q,dt.c,bt.b,ut.a,o.t,r.a,K.c,d.e,mt.b,ht.b,pt.b,j.b,_.b,B.b]]}),t})()}}]);