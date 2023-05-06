<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building" />
<html>
<head>
    <title>Thêm tòa nhà</title>
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
                    <form:form modelAttribute="buildings" action="/api/building" id="formEdit" method="GET" class="form-horizontal" role="form">

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="name">Tên tòa nhà</label>
                            <div class="col-sm-9">
                                <input type="text" id="name" class="form-control" name="name" value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="district">Quận hiện có</label>
                            <form:select path="district" class="col-sm-9">
                                <form:option value="" label="---Chọn Quận---" />
                                <form:options value="${districts.get(0)}" items="${districts}"/>
                            </form:select>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="ward">Phường</label>
                            <div class="col-sm-9">
                                <input type="text" id="ward" class="form-control" name="ward" value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="street">Đường</label>
                            <div class="col-sm-9">
                                <input type="text" id="street" class="form-control" name="street" value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="structure">Kết cấu</label>
                            <div class="col-sm-9">
                                <input type="text" id="structure" class="form-control" name="structure" value=""/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="numberOfBasement">Số tầng hầm</label>
                            <div class="col-sm-9">
                                <input type="number" id="numberOfBasement" class="form-control" name="numberOfBasement"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="floorArea">Diện tích sàn</label>
                            <div class="col-sm-9">
                                <input type="number" id="floorArea" class="form-control" name="floorArea"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="direction">Hướng</label>
                            <div class="col-sm-9">
                                <input type="text" id="direction" class="form-control" name="direction"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="level">Hạng</label>
                            <div class="col-sm-9">
                                <input type="text" id="level" class="form-control" name="level"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" >Diện tích thuê</label>
                            <div class="col-sm-9">
                                <input type="text" id="rentAreas" class="form-control" name="rentAreas"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentpricedescription">Mô tả giá thuê</label>
                            <div class="col-sm-9">
                                <input type="text" id="rentpricedescription" class="form-control" name="rentpricedescription"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="servicefee">Phí dịch vụ</label>
                            <div class="col-sm-9">
                                <input type="text" id="servicefee" class="form-control" name="servicefee"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="carfee">Phí ô tô</label>
                            <div class="col-sm-9">
                                <input type="text" id="carfee" class="form-control" name="carfee"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="motorbikefee">Phí mô tô</label>
                            <div class="col-sm-9">
                                <input type="text" id="motorbikefee" class="form-control" name="motorbikefee"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="overtimefee">Phí ngoài giờ</label>
                            <div class="col-sm-9">
                                <input type="text" id="overtimefee" class="form-control" name="overtimefee"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="waterfee">Tiền nước</label>
                            <div class="col-sm-9">
                                <input type="text" id="waterfee" class="form-control" name="waterfee"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="electricityfee">Tiền điện</label>
                            <div class="col-sm-9">
                                <input type="text" id="electricityfee" class="form-control" name="electricityfee"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="deposit">Đặt cọc</label>
                            <div class="col-sm-9">
                                <input type="text" id="deposit" class="form-control" name="deposit"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="payment">Thanh toán</label>
                            <div class="col-sm-9">
                                <input type="text" id="payment" class="form-control" name="payment"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="renttime">Thời hạn thuê</label>
                            <div class="col-sm-9">
                                <input type="text" id="renttime" class="form-control" name="renttime"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="decorationtime">Thời gian trang trí</label>
                            <div class="col-sm-9">
                                <input type="text" id="decorationtime" class="form-control" name="decorationtime"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="brokeragefee">Phí môi giới</label>
                            <div class="col-sm-9">
                                <input type="text" id="brokeragefee" class="form-control" name="brokeragefee"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="managerName">Tên quản lý</label>
                            <div class="col-sm-9">
                                <input type="text" id="managerName" class="form-control" name="managerName"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="managerPhone">Số điện thoại quản lý</label>
                            <div class="col-sm-9">
                                <input type="text" id="managerPhone" class="form-control" name="managerPhone"/>
                            </div>
                        </div>



                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" >Loại tòa nhà</label>
                            <div class="col-sm-9">
                                <%--<label class="checkbox-inline"><input type="checkbox" value="TANG_TRET" name="type">Tầng trệt</label>--%>
                                <%--<label class="checkbox-inline"><input type="checkbox" value="NGUYEN_CAN" name="type">Nguyên căn</label>--%>
                                <%--<label class="checkbox-inline"><input type="checkbox" value="NOI_THAT" name="type">Nội thất</label>--%>

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



                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"></label>

                            <div class="col-sm-9">
                                <button type="button" class="btn btn-primary" id="btnAddBuilding">Thêm tòa nhà</button>
                                <button type="button" class="btn btn-primary">Hủy</button>
                            </div>
                        </div>
                    </form:form>
                </div>

            </div><!-- /.row -->
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->

<script>
    $('#btnAddBuilding').click(function (e){
        e.preventDefault();
        //call api add building
        var data = {};
        var buildingTypes = [];
        var rentAreas = [];
        var formData = $('#formEdit').serializeArray();
        $.each(formData, function(index, v){
            if(v.name == 'buildingTypes'){
                buildingTypes.push(v.value);
            }else{
                if(v.value != ''){
                    data[""+v.name+""] = v.value;
                }else {
                    data[""+v.name+""] = null;
                }
            }
        });
        data['type'] = buildingTypes;
        $.ajax({
            type: 'POST',
            url: '${buildingAPI}',
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
    });
</script>

</body>
</html>
