'use strict';
import { mdc } from './../mdc.controller.js';

const MDCBanner = mdc.banner.MDCBanner;
const MDCBannerFoundation = mdc.banner.MDCBannerFoundation;
const MDCComponent = mdc.base.MDCComponent;
const MDCFoundation = mdc.base.MDCFoundation;
const MDCCheckbox = mdc.checkbox.MDCCheckbox;
const MDCCheckboxFoundation = mdc.checkbox.MDCCheckboxFoundation;
const MDCChipTrailingAction = mdc.chips.MDCChipTrailingAction;
const MDCChipTrailingActionFoundation = mdc.chips.MDCChipTrailingActionFoundation;
const MDCChip = mdc.chips.MDCChip;
const MDCChipFoundation = mdc.chips.MDCChipFoundation;
const MDCChipSet = mdc.chips.MDCChipSet;
const MDCChipSetFoundation = mdc.chips.MDCChipSetFoundation;
const MDCCircularProgress = mdc.circularProgress.MDCCircularProgress;
const MDCCircularProgressFoundation = mdc.circularProgress.MDCCircularProgressFoundation;
const MDCDataTable = mdc.dataTable.MDCDataTable;
const MDCDataTableFoundation = mdc.dataTable.MDCDataTableFoundation;
const MDCDialog = mdc.dialog.MDCDialog;
const MDCDialogFoundation = mdc.dialog.MDCDialogFoundation;
const MDCDrawer = mdc.drawer.MDCDrawer;
const MDCDismissibleDrawerFoundation = mdc.drawer.MDCDismissibleDrawerFoundation;
const MDCModalDrawerFoundation = mdc.drawer.MDCModalDrawerFoundation;
const MDCFloatingLabel = mdc.floatingLabel.MDCFloatingLabel;
const MDCFloatingLabelFoundation = mdc.floatingLabel.MDCFloatingLabelFoundation;
const MDCFormField = mdc.formField.MDCFormField;
const MDCFormFieldFoundation = mdc.formField.MDCFormFieldFoundation;
const MDCIconButtonToggle = mdc.iconButton.MDCIconButtonToggle;
const MDCIconButtonToggleFoundation = mdc.iconButton.MDCIconButtonToggleFoundation;
const MDCLineRipple = mdc.lineRipple.MDCLineRipple;
const MDCLineRippleFoundation = mdc.lineRipple.MDCLineRippleFoundation;
const MDCLinearProgress = mdc.linearProgress.MDCLinearProgress;
const MDCLinearProgressFoundation = mdc.linearProgress.MDCLinearProgressFoundation;
const MDCList = mdc.list.MDCList;
const MDCListFoundation = mdc.list.MDCListFoundation;
const MDCMenu = mdc.menu.MDCMenu;
const MDCMenuFoundation = mdc.menu.MDCMenuFoundation;
const MDCMenuSurface = mdc.menuSurface.MDCMenuSurface;
const MDCMenuSurfaceFoundation = mdc.menuSurface.MDCMenuSurfaceFoundation;
const MDCNotchedOutline = mdc.notchedOutline.MDCNotchedOutline;
const MDCNotchedOutlineFoundation = mdc.notchedOutline.MDCNotchedOutlineFoundation;
const MDCRadio = mdc.radio.MDCRadio;
const MDCRadioFoundation = mdc.radio.MDCRadioFoundation;
const MDCRipple = mdc.ripple.MDCRipple;
const MDCRippleFoundation = mdc.ripple.MDCRippleFoundation;
const MDCSegmentedButtonFoundation = mdc.segmentedButton.MDCSegmentedButtonFoundation;
const MDCSegmentedButton = mdc.segmentedButton.MDCSegmentedButton;
const MDCSegmentedButtonSegmentFoundation = mdc.segmentedButton.MDCSegmentedButtonSegmentFoundation;
const MDCSegmentedButtonSegment = mdc.segmentedButton.MDCSegmentedButtonSegment;
const MDCSelect = mdc.select.MDCSelect;
const MDCSelectFoundation = mdc.select.MDCSelectFoundation;
const MDCSelectHelperText = mdc.select.MDCSelectHelperText;
const MDCSelectHelperTextFoundation = mdc.select.MDCSelectHelperTextFoundation;
const MDCSelectIcon = mdc.select.MDCSelectIcon;
const MDCSelectIconFoundation = mdc.select.MDCSelectIconFoundation;
const MDCSlider = mdc.slider.MDCSlider;
const MDCSliderFoundation = mdc.slider.MDCSliderFoundation;
const MDCSnackbar = mdc.snackbar.MDCSnackbar;
const MDCSnackbarFoundation = mdc.snackbar.MDCSnackbarFoundation;
const MDCSwitch = mdc.switchControl.MDCSwitch;
const MDCSwitchFoundation = mdc.switchControl.MDCSwitchFoundation;
const MDCSwitchRenderFoundation = mdc.switchControl.MDCSwitchRenderFoundation;
const MDCTab = mdc.tab.MDCTab;
const MDCTabFoundation = mdc.tab.MDCTabFoundation;
const MDCTabBar = mdc.tabBar.MDCTabBar;
const MDCTabBarFoundation = mdc.tabBar.MDCTabBarFoundation;
const MDCTabIndicator = mdc.tabIndicator.MDCTabIndicator;
const MDCTabIndicatorFoundation = mdc.tabIndicator.MDCTabIndicatorFoundation;
const MDCFadingTabIndicatorFoundation = mdc.tabIndicator.MDCFadingTabIndicatorFoundation;
const MDCSlidingTabIndicatorFoundation = mdc.tabIndicator.MDCSlidingTabIndicatorFoundation;
const MDCTabScroller = mdc.tabScroller.MDCTabScroller;
const MDCTabScrollerFoundation = mdc.tabScroller.MDCTabScrollerFoundation;
const MDCTextField = mdc.textField.MDCTextField;
const MDCTextFieldFoundation = mdc.textField.MDCTextFieldFoundation;
const MDCTextFieldCharacterCounter = mdc.textField.MDCTextFieldCharacterCounter;
const MDCTextFieldCharacterCounterFoundation = mdc.textField.MDCTextFieldCharacterCounterFoundation;
const MDCTextFieldHelperText = mdc.textField.MDCTextFieldHelperText;
const MDCTextFieldHelperTextFoundation = mdc.textField.MDCTextFieldHelperTextFoundation;
const MDCTextFieldIcon = mdc.textField.MDCTextFieldIcon;
const MDCTextFieldIconFoundation = mdc.textField.MDCTextFieldIconFoundation;
const MDCTooltip = mdc.tooltip.MDCTooltip;
const MDCTooltipFoundation = mdc.tooltip.MDCTooltipFoundation;
const MDCTopAppBar = mdc.topAppBar.MDCTopAppBar;
const MDCTopAppBarBaseFoundation = mdc.topAppBar.MDCTopAppBarBaseFoundation;
const MDCFixedTopAppBarFoundation = mdc.topAppBar.MDCFixedTopAppBarFoundation;
const MDCShortTopAppBarFoundation = mdc.topAppBar.MDCShortTopAppBarFoundation;
const MDCTopAppBarFoundation = mdc.topAppBar.MDCTopAppBarFoundation;
export {
    MDCBanner,
    MDCBannerFoundation,
    MDCComponent,
    MDCFoundation,
    MDCCheckbox,
    MDCCheckboxFoundation,
    MDCChipTrailingAction,
    MDCChipTrailingActionFoundation,
    MDCChip,
    MDCChipFoundation,
    MDCChipSet,
    MDCChipSetFoundation,
    MDCCircularProgress,
    MDCCircularProgressFoundation,
    MDCDataTable,
    MDCDataTableFoundation,
    MDCDialog,
    MDCDialogFoundation,
    MDCDrawer,
    MDCDismissibleDrawerFoundation,
    MDCModalDrawerFoundation,
    MDCFloatingLabel,
    MDCFloatingLabelFoundation,
    MDCFormField,
    MDCFormFieldFoundation,
    MDCIconButtonToggle,
    MDCIconButtonToggleFoundation,
    MDCLineRipple,
    MDCLineRippleFoundation,
    MDCLinearProgress,
    MDCLinearProgressFoundation,
    MDCList,
    MDCListFoundation,
    MDCMenu,
    MDCMenuFoundation,
    MDCMenuSurface,
    MDCMenuSurfaceFoundation,
    MDCNotchedOutline,
    MDCNotchedOutlineFoundation,
    MDCRadio,
    MDCRadioFoundation,
    MDCRipple,
    MDCRippleFoundation,
    MDCSegmentedButtonFoundation,
    MDCSegmentedButton,
    MDCSegmentedButtonSegmentFoundation,
    MDCSegmentedButtonSegment,
    MDCSelect,
    MDCSelectFoundation,
    MDCSelectHelperText,
    MDCSelectHelperTextFoundation,
    MDCSelectIcon,
    MDCSelectIconFoundation,
    MDCSlider,
    MDCSliderFoundation,
    MDCSnackbar,
    MDCSnackbarFoundation,
    MDCSwitch,
    MDCSwitchFoundation,
    MDCSwitchRenderFoundation,
    MDCTab,
    MDCTabFoundation,
    MDCTabBar,
    MDCTabBarFoundation,
    MDCTabIndicator,
    MDCTabIndicatorFoundation,
    MDCFadingTabIndicatorFoundation,
    MDCSlidingTabIndicatorFoundation,
    MDCTabScroller,
    MDCTabScrollerFoundation,
    MDCTextField,
    MDCTextFieldFoundation,
    MDCTextFieldCharacterCounter,
    MDCTextFieldCharacterCounterFoundation,
    MDCTextFieldHelperText,
    MDCTextFieldHelperTextFoundation,
    MDCTextFieldIcon,
    MDCTextFieldIconFoundation,
    MDCTooltip,
    MDCTooltipFoundation,
    MDCTopAppBar,
    MDCTopAppBarBaseFoundation,
    MDCFixedTopAppBarFoundation,
    MDCShortTopAppBarFoundation,
    MDCTopAppBarFoundation,
}