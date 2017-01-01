package tiquartet.ClientModule.ui.customnode;

import java.rmi.RemoteException;
import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import tiquartet.ClientModule.ui.datastorage.DistrictData;
import tiquartet.ClientModule.ui.rmiclient.HMSClient;
import tiquartet.CommonModule.util.DigitToChinese;
import tiquartet.CommonModule.util.StringUtility;
import tiquartet.CommonModule.vo.StrategyVO;

public class StrategyPane extends FlowPane {
	StrategyVO vo;
	Label typeLabel;
	TextArea introArea;
	Label districtLabel;
	ChoiceBox<String> cityBox;
	ChoiceBox<String> districtBox;
	TextField companyField;
	TextField roomNumField;
	DatePicker startDatePicker;
	DatePicker endDatePicker;
	Button modifyButton;
	Button cancelButton;
	Label discountLabel;
	TextField discountField;

	public StrategyPane(StrategyVO vo) {
		this.vo = vo;
		if (vo.strategyID == 1) {
			typeLabel = new Label("会员升级信用值门槛");
			typeLabel.setFont(new Font(18));
			getChildren().add(typeLabel);
			String intro = "";
			for (int i = 1; i < 10; i++) {
				intro += DigitToChinese.toChinese(i + 1) + "级会员："
						+ vo.memberThreShold[i - 1] + "；";
			}
			introArea = new TextArea(intro);
			introArea.setFont(new Font(14));
			introArea.setPrefWidth(550);
			introArea.setPrefHeight(120);
			introArea.setWrapText(true);
			introArea.setEditable(false);
			getChildren().add(introArea);
		} else if (vo.strategyID == 2) {
			typeLabel = new Label("会员等级折扣");
			typeLabel.setFont(new Font(18));
			getChildren().add(typeLabel);
			String intro = "";
			for (int i = 1; i < 10; i++) {
				intro += DigitToChinese.toChinese(i + 1) + "级会员："
						+ (vo.memberDiscount[i - 1] * 100) + "%；";
			}
			introArea = new TextArea(intro);
			introArea.setFont(new Font(14));
			introArea.setPrefWidth(550);
			introArea.setPrefHeight(120);
			introArea.setWrapText(true);
			introArea.setEditable(false);
			getChildren().add(introArea);
		} else {
			typeLabel = new Label(
					"策略类型：" + StringUtility.strategyName(vo.strategyType));
			typeLabel.setFont(new Font(18));
			getChildren().add(typeLabel);
			introArea = new TextArea(vo.strategyIntro);
			introArea.setFont(new Font(14));
			introArea.setPrefWidth(550);
			introArea.setPrefHeight(120);
			introArea.setWrapText(true);
			introArea.setEditable(false);
			getChildren().add(introArea);

			discountLabel = new Label("折扣比例（%）");
			discountLabel.setFont(new Font(14));
			discountField = new TextField();
			discountField.setEditable(false);
			discountField.setText(String.valueOf(vo.discount * 100));
			discountField.setFont(new Font(14));
			discountField.setPromptText("如78、97.5");

			switch (vo.strategyType) {
				case BIRTHDAY :

					break;
				case CIRCLE :
					districtLabel = new Label("商圈："
							+ DistrictData.getCityMap().get(vo.circleID / 100)
							+ "，"
							+ DistrictData.getDistrictMap().get(vo.circleID));
					typeLabel.setFont(new Font(18));
					getChildren().add(districtLabel);

					break;
				case COMPANY :
					Label companyLabel = new Label("合作企业客户");
					companyLabel.setFont(new Font(18));
					getChildren().add(companyLabel);
					companyField = new TextField();
					companyField.setEditable(false);
					companyField.setText(vo.company);
					companyField.setFont(new Font(14));
					getChildren().add(companyField);
					break;
				case ROOMNUM :
					Label roomNumLabel = new Label("客房数量下限");
					roomNumLabel.setFont(new Font(18));
					getChildren().add(roomNumLabel);
					roomNumField = new TextField();
					roomNumField.setEditable(false);
					roomNumField.setText(String.valueOf(vo.numOfRoom));
					roomNumField.setFont(new Font(14));
					getChildren().add(roomNumField);
					break;
				case TIME :
					Label startDateLabel = new Label("优惠开始日期");
					startDateLabel.setFont(new Font(18));
					getChildren().add(startDateLabel);
					startDatePicker = new DatePicker();
					startDatePicker.setEditable(false);
					startDatePicker.setDisable(true);
					setMargin(startDateLabel, new Insets(0, 100, 0, 0));
					if (!vo.startTime.isEmpty())
						startDatePicker.setValue(LocalDate.parse(vo.startTime));
					getChildren().add(startDatePicker);

					Label endDateLabel = new Label("优惠结束日期");
					endDateLabel.setFont(new Font(18));
					getChildren().add(endDateLabel);
					endDatePicker = new DatePicker();
					endDatePicker.setEditable(false);
					endDatePicker.setDisable(true);
					setMargin(endDateLabel, new Insets(0, 100, 0, 0));
					if (!vo.endTime.isEmpty())
						endDatePicker.setValue(LocalDate.parse(vo.endTime));
					getChildren().add(endDatePicker);
					break;
				default :
					break;

			}
			getChildren().add(discountLabel);
			getChildren().add(discountField);

			modifyButton = new Button("修改");
			modifyButton.setTextFill(Color.WHITE);
			modifyButton.setBackground(new Background(
					new BackgroundFill(Color.STEELBLUE, null, null)));
			modifyButton.setFont(new Font(18));
			modifyButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if (modifyButton.getText().equals("修改")) {
						switch (vo.strategyType) {
							case BIRTHDAY :
								break;
							case CIRCLE :
								int index = getChildren()
										.indexOf(districtLabel);
								getChildren().remove(index);
								cityBox = new ChoiceBox<>();
								districtBox = new ChoiceBox<>();
								districtBox.setDisable(true);
								cityBox.getItems().addAll(
										DistrictData.getCityMap().values());
								cityBox.getSelectionModel().clearSelection();
								cityBox.getSelectionModel()
										.selectedIndexProperty().addListener(
												new ChangeListener<Number>() {
													@Override
													public void changed(
															ObservableValue<? extends Number> observable,
															Number oldValue,
															Number newValue) {
														if ((Integer) newValue == -1) {
															districtBox
																	.setDisable(
																			true);
															return;
														} else
															districtBox
																	.setDisable(
																			false);
														String city = cityBox
																.getItems()
																.get((Integer) newValue);
														// cityID =
														// DistrictData.getCityIDOf(city);
														districtBox.getItems()
																.clear();
														districtBox.getItems()
																.addAll(DistrictData
																		.districtNameListOfCity(
																				DistrictData
																						.getCityIDOf(
																								city)));
														districtBox
																.getSelectionModel()
																.clearSelection();
														districtBox.setDisable(
																false);
													}
												});
								getChildren().add(index, districtBox);
								getChildren().add(index, cityBox);
								break;
							case COMPANY :
								companyField.setEditable(true);
								break;
							case ROOMNUM :
								roomNumField.setEditable(true);
								break;
							case TIME :
								startDatePicker.setEditable(true);
								endDatePicker.setEditable(true);
								startDatePicker.setDisable(false);
								endDatePicker.setDisable(false);
								break;
							default :
								break;

						}
						introArea.setEditable(true);
						discountField.setEditable(true);
						modifyButton.setText("确认");
						cancelButton.setVisible(true);
					} else {
						switch (vo.strategyType) {
							case BIRTHDAY :
								break;
							case CIRCLE :
								int index = getChildren().indexOf(cityBox);
								int cityID = DistrictData.getCityIDOf(cityBox
										.getSelectionModel().getSelectedItem());
								String districtName = districtBox.getItems()
										.get(districtBox.getSelectionModel()
												.getSelectedIndex());
								vo.circleID = DistrictData
										.districtIDListOfCity(cityID)
										.get(DistrictData
												.districtNameListOfCity(cityID)
												.indexOf(districtName));
								districtLabel = new Label("商圈："
										+ cityBox.getSelectionModel()
												.getSelectedItem()
										+ "，" + districtBox.getSelectionModel()
												.getSelectedItem());
								getChildren().remove(index);
								getChildren().remove(index);
								getChildren().add(index, districtLabel);
								break;
							case COMPANY :
								vo.company = companyField.getText();
								companyField.setEditable(false);
								break;
							case ROOMNUM :
								vo.numOfRoom = Integer
										.parseInt(roomNumField.getText());
								roomNumField.setEditable(false);
								break;
							case TIME :
								vo.startTime = startDatePicker.getValue()
										.toString();
								vo.endTime = endDatePicker.getValue()
										.toString();
								startDatePicker.setDisable(true);
								endDatePicker.setDisable(true);
								break;
							default :
								break;
						}
						vo.strategyIntro = introArea.getText();
						vo.discount = Double
								.parseDouble(discountField.getText()) / 100.0;
						discountField.setEditable(false);

						try {
							HMSClient.getStrategyBL().modifyStrategy(vo);
							modifyButton.setText("修改");
							cancelButton.setVisible(false);
						} catch (RemoteException e) {
							// 网络异常处理
							e.printStackTrace();
						}
					}
				}
			});
			cancelButton = new Button("取消");
			cancelButton.setTextFill(Color.WHITE);
			cancelButton.setBackground(new Background(
					new BackgroundFill(Color.STEELBLUE, null, null)));
			cancelButton.setFont(new Font(18));
			cancelButton.setVisible(false);
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					discountLabel = new Label("折扣比例（%）");
					discountLabel.setFont(new Font(14));
					discountField = new TextField();
					discountField.setEditable(false);
					discountField.setText(String.valueOf(vo.discount * 100));
					discountField.setFont(new Font(14));
					discountField.setPromptText("如78、97.5");

					switch (vo.strategyType) {
						case BIRTHDAY :
							break;
						case CIRCLE :
							int index = getChildren().indexOf(cityBox);
							getChildren().remove(index);
							getChildren().remove(index);
							getChildren().add(index, districtLabel);
							break;
						case COMPANY :
							companyField.setEditable(false);
							companyField.setText(vo.company);
						case ROOMNUM :
							roomNumField.setEditable(false);
							roomNumField.setText(String.valueOf(vo.numOfRoom));
							break;
						case TIME :
							startDatePicker.setEditable(false);
							startDatePicker.setDisable(true);
							startDatePicker
									.setValue(LocalDate.parse(vo.startTime));

							endDatePicker.setEditable(false);
							endDatePicker.setDisable(true);
							endDatePicker.setValue(LocalDate.parse(vo.endTime));
							break;
						default :
							break;
					}
					introArea.setText(vo.strategyIntro);
					introArea.setEditable(false);
					discountField.setEditable(false);
					discountField.setText(String.valueOf(vo.discount * 100));
					modifyButton.setText("修改");
					cancelButton.setVisible(false);
				}
			});
			getChildren().add(modifyButton);
			getChildren().add(cancelButton);

		}
		introArea.setPromptText("优惠策略介绍");

		setPrefWidth(640);
		setPrefHeight(USE_COMPUTED_SIZE);
		setPrefWrapLength(0);
		setVgap(20);
		setHgap(30);
		setPadding(new Insets(20));
	}

}
