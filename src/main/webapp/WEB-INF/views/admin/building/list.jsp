<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="loadStaffAPI" value="/api/building" />
<c:url var="assignmentbuildingAPI" value="/api/assignmentbuilding" />

<c:url var="buildingListURL" value="/admin/building-list" />
<html>
<head>
    <title>Danh Sách Tòa Nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm</h4>

                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form modelAttribute="modelSearch" action="${buildingListURL}" id="listForm" method="GET" >
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <!-- PAGE CONTENT BEGINS -->
                                            <div class="col-sm-6">
                                                <div>
                                                    <label for="name">Tên toà nhà</label>
                                                    <form:input path="name" cssClass="form-control" name="name" value="${modelSearch.name}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-6">
                                                <div>
                                                    <label for="floorArea">Diện tích sàn</label>
                                                    <input type="number" id="floorArea" class="form-control" name="floorArea" value="${modelSearch.floorArea}"/>
                                                </div>
                                            </div>
                                            <!-- PAGE CONTENT ENDS -->
                                        </div><!-- /.col -->
                                    </div>


                                    <div class="row">
                                        <div class="col-xs-12">
                                            <!-- PAGE CONTENT BEGINS -->
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="district">Quận hiện có</label>
                                                    <form:select path="district" class="form-control">
                                                        <form:option value="" label="---Chọn Quận---" />
                                                        <form:options value="${districts.get(0)}" items="${districts}"/>
                                                    </form:select>

                                                        <%--<label class="col-sm-3 control-label no-padding-right">Quận</label>--%>
                                                        <%--<select class="col-sm-9" name="district">--%>
                                                        <%--<option value="">---Chọn Quận---</option>--%>
                                                        <%--<option value="Q1">Quận 1</option>--%>
                                                        <%--<option value="Q2">Quận 2</option>--%>
                                                        <%--<option value="Q4">Quận 4</option>--%>
                                                        <%--<option value="Q6">Quận 6</option>--%>
                                                        <%--</select>--%>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="ward">Phường</label>
                                                    <form:input path="ward" cssClass="form-control" name="ward" value="${modelSearch.ward}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="street">Đường</label>
                                                    <form:input path="street" cssClass="form-control" name="street" value="${modelSearch.street}"/>
                                                </div>
                                            </div>
                                            <!-- PAGE CONTENT ENDS -->
                                        </div><!-- /.col -->
                                    </div>


                                    <div class="row">
                                        <div class="col-xs-12">
                                            <!-- PAGE CONTENT BEGINS -->
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="numberOfBasement">Số tầng hầm</label>
                                                    <input type="number" id="numberOfBasement"
                                                           class="form-control" name="numberOfBasement" value="${modelSearch.numberOfBasement}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="direction">Hướng</label>
                                                    <input type="text" id="direction" class="form-control" name="direction" value="${modelSearch.direction}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="level">Hạng</label>
                                                    <input type="number" id="level" class="form-control" name="level" value="${modelSearch.level}"/>
                                                </div>
                                            </div>
                                            <!-- PAGE CONTENT ENDS -->
                                        </div><!-- /.col -->
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-12">
                                            <!-- PAGE CONTENT BEGINS -->
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentAreaFrom">Diện tích từ</label>
                                                    <input type="number" id="rentAreaFrom" class="form-control" name="rentAreaFrom" value="${modelSearch.rentAreaFrom}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentAreaTo">Diện tích đến</label>
                                                    <input type="number" id="rentAreaTo" class="form-control" name="rentAreaTo" value="${modelSearch.rentAreaTo}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentPirceFrom">Giá thuê từ</label>
                                                    <input type="number" id="rentPirceFrom" class="form-control" name="rentAreaTo" value="${modelSearch.rentPirceFrom}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label for="rentPirceTo">Giá thuê đến</label>
                                                    <input type="number" id="rentPirceTo" class="form-control" name="rentPirceTo" value="${modelSearch.rentPirceTo}"/>
                                                </div>
                                            </div>
                                            <!-- PAGE CONTENT ENDS -->
                                        </div><!-- /.col -->
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-12">
                                            <!-- PAGE CONTENT BEGINS -->
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="managerName">Tên quản lý</label>
                                                    <input type="text" id="managerName" class="form-control" name="managerName" value="${modelSearch.managerName}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="managerPhone">Điện thoại quản lý</label>
                                                    <input type="number" id="managerPhone" class="form-control" name="managerPhone" value="${modelSearch.managerPhone}"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label for="staffId">Chọn nhân viên phụ trách</label>
                                                    <%--<select class="form-control" id="staffId">--%>
                                                        <%--<option value="">---Chọn nhân viên phụ trách---</option>--%>
                                                        <%--<option>Nhân viên 1</option>--%>
                                                        <%--<option>Nhân viên 2</option>--%>
                                                        <%--<option>Nhân viên 3</option>--%>
                                                        <%--<option>Nhân viên 4</option>--%>
                                                        <%--<option>Nhân viên 5</option>--%>
                                                        <%--<option>Nhân viên 6</option>--%>
                                                        <%--<option>Nhân viên 7</option>--%>
                                                    <%--</select>--%>
                                                    <form:select path="staffId" class="form-control">
                                                        <form:option value="" label="---Chọn nhân viên phụ trách---" />
"                                                       <form:options value="${staffmaps.get(1)}" items="${staffmaps}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <!-- PAGE CONTENT ENDS -->
                                        </div><!-- /.col -->
                                    </div>

                                    <div class="row">
                                        <div class="col-xs-12">
                                            <div class="checkbox">
                                                <c:forEach var="item" items="${buildingTypes}">
                                                    <label class="checkbox-inline" for="buildingTypes">
                                                        <input type="checkbox" value="${item.key}" ${fn:contains(fn:join(mav.buildingTypes , ','), item.key) ? 'checked' :''} name="buildingTypes" id="buildingTypes">
                                                        <b>${item.value}</b>
                                                    </label>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="button" class="btn btn-success" id="btnSearch">Tìm
                                                kiếm <i class='fa fa-arrow-right'></i></button>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>

                    <div class="pull-right">
                        <Button class="btn btn-white btn-infor btn-bold" data-toggle="tooltip"
                                title="Thêm tòa nhà">
                            <a href="http://localhost:8080/admin/building-edit">
                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                            </a>
                        </Button>

                        <Button class="btn btn-white btn-warning btn-bold" data-toggle="tooltip"
                                title="Xóa tòa nhà" id="btnDeleteBuilding">
                            <i class="fa fa-trash" aria-hidden="true"></i>
                        </Button>
                    </div>
                </div>

            </div><!-- /.row -->
            <br>

            <div class="row">
                <div class="col-xs-12">
                    <table id="buildingList" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Tên sản phẩm</th>
                            <th>Địa chỉ</th>
                            <th>Tên quản lý</th>
                            <th>Số điện thoại</th>
                            <th>Diện tích sàn</th>
                            <th>Giá thuê</th>
                            <th>Phí dịch vụ</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${buildings}">
                            <tr>
                                <td><input type="checkbox" value="${item.id}" id="checkbox_${item.id}"></td>
                                <td>${item.name}</td>
                                <td>${item.street}</td>
                                <td>${item.managerName}</td>
                                <td>${item.managerPhone}</td>
                                <td>${item.floorArea}</td>
                                <td>${item.rentprice}</td>
                                <td>${item.servicefee}</td>
                                <td>
                                    <button class="btn btn-xs btn-info " data-toggle="tooltip"
                                            title="Giao toa nha" onclick="assignmentBuilding(${item.id})">
                                        <i class="ace-icon fa fa-user bigger-120"></i>
                                    </button>

                                    <button class="btn btn-xs btn-info " data-toggle="tooltip"
                                            title="Sửa tòa nhà">
                                        <a href="<c:url value="/admin/building-update?id=${item.id}"/>">
                                                <%--<a href="http://localhost:8080/admin/building-update?${item.id}">--%>
                                            <i class="ace-icon fa fa-pencil bigger-120">

                                            </i>
                                        </a>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<!-- Modal -->
<div class="modal fade" id="assignmentBuildingModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn nhân viên</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <input type="hidden" name="buildingId" id="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignBuilding">Giao toa nha</button>

                <button type="button" class="btn btn-default" data-dismiss="modal">Dong lai</button>
            </div>
        </div>

    </div>
</div>

<script>
    function assignmentBuilding(buildingId) {
        openModalAssignmentBuilding();

        $('#buildingId').val(buildingId);
        var buildingID = buildingId;
        loadStaff($('#buildingId').val());
    }
    
    function loadStaff(buildingId) {
        var ull = "${loadStaffAPI}/" + buildingId + "/staffs";
        $.ajax({
            type: "GET",
            url: ull,
            dataType: "json", //Dữ liệu từ
            success: function (respond) {
                console.log('success');
                var row = '';
                $.each(respond.data, function (index, item) {
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value="' +item.staffId+ '" id="checkbox_' +item.staffId+ '" class="check-box-element" ' +item.checked+ '></td>';
                    <%--<td><input type="checkbox" value="${item.id}" id="checkbox_1"></td>--%>
                    row += '<td class="text-center">' +item.fullName+ '</td>';
                    row += '</tr>'
                })
                $('#staffList tbody').html(row);
            },
            error: function (respond) {
                console.log('failed');
                console.log(respond);
            },
        });
    }

    function openModalAssignmentBuilding() {
        $('#assignmentBuildingModal').modal();
    }

    $('#btnAssignBuilding').click(function (e) {
        e.preventDefault();
        //call api
        var data = {};
        data['buildingId'] = $('#buildingId').val();
        //$('#staffList').find('tbody input[type=checkbox]');
        var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        assignStaff(data);
    });

    function assignStaff(data) {
        $.ajax({
            type: "POST",
            url: '${assignmentbuildingAPI}',
            data: JSON.stringify(data),
            dataType: "json", //Dữ liệu từ
            contentType: "application/json",
            success: function (respond) {
                console.log('success');
            },
            error: function (respond) {
                console.log('failed');
                console.log(respond);
            },
        });
    }

    $('#btnDeleteBuilding').click(function (e) {
        e.preventDefault();
        //call api
        var data = {};
        var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['ids'] = buildingIds;
        deleteBuilding(data);

    });

    function deleteBuilding(data) {
        $.ajax({
            type: "DELETE",
            // url: "http://localhost:8080/api/building",
            url: '${loadStaffAPI}',
            data: JSON.stringify(data),
            dataType: "json", //Dữ liệu từ
            contentType: "application/json",
            success: function(respond){
                console.log('success');
            },
            error: function(respond){
                console.log('failed');
                console.log(respond);
            },
        });
    }

    $('#btnSearch').click(function (e) {
        e.preventDefault();
        //call api
        $('#listForm').submit();

    });
</script>
</body>
</html>
